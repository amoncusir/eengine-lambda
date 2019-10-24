package info.digitalpoet.eengine.core.orchestrator

import com.nhaarman.mockitokotlin2.mock
import info.digitalpoet.eengine.core.deliverer.Deliverer
import info.digitalpoet.eengine.core.deliverer.TryDeliveryDelegate
import info.digitalpoet.eengine.core.message.ErrorMessageDelegate
import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.MessageConfiguration
import info.digitalpoet.eengine.core.mockSubscriber
import info.digitalpoet.eengine.core.subscriber.Subscriber
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import kotlinx.coroutines.plus
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import kotlin.random.Random

/** <!-- Documentation for: info.digitalpoet.eengine.core.orchestrator.AsyncOrchestratorTest on 14/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class AsyncOrchestratorTest
{
    internal class TryDeliveryDelegateMock(val result: Pair<Boolean, Throwable?>): TryDeliveryDelegate
    {
        override suspend fun tryToDelivery(
            message: Message, subscriber: Subscriber, configuration: MessageConfiguration, fn: suspend () -> Unit
        ): Pair<Boolean, Throwable?>
        {
            fn.invoke()
            return result
        }
    }

    internal class DelivererMock(val args: MutableList<Pair<Int, Int>> = mutableListOf()): Deliverer
    {
        override suspend fun delivery(message: Message)
        {
            delay(Random.nextLong(10, 30))
            args.add(args.size to message.id.toInt())
        }
    }

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    //~ Tests ==========================================================================================================

    @Test
    fun `test coroutines delivery`() = runBlocking {
        val configuration = MessageConfiguration("type")
        val errorMessageDelegate: ErrorMessageDelegate = mock {  }
        val tryDeliveryDelegate = TryDeliveryDelegateMock(true to null)
        val deliverer = DelivererMock()
        val subscriber: Subscriber = mockSubscriber("id", deliverer)

        val request = CoroutineScope(Dispatchers.IO).launch {
            val orchestrator = AsyncOrchestrator(errorMessageDelegate, tryDeliveryDelegate, this)

            for (i in 0..100)
            {
                orchestrator.put(Message("$i", "channel-$i", byteArrayOf(), mock {}, mock {}), subscriber, configuration)
            }
        }

        request.join()

        println(deliverer.args)

        val asyncPuts = deliverer.args.filter { (a, b) -> a == b }.size

        assertThat(asyncPuts, not(equalTo(deliverer.args.size)))
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
