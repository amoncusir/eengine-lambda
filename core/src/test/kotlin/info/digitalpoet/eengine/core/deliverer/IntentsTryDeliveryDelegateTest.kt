package info.digitalpoet.eengine.core.deliverer

import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.isA
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

/** <!-- Documentation for: info.digitalpoet.eengine.core.deliverer.IntentsTryDeliveryDelegateTest on 14/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class IntentsTryDeliveryDelegateTest
{
    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    //~ Tests ==========================================================================================================

    @Test
    fun `test do all correct intents`()
    {
        val maxIntents = 3
        val deliveryDelegate = IntentsTryDeliveryDelegate(maxIntents)
        val fn: () -> Unit = mock { onGeneric { invoke() } doThrow RuntimeException("Test error") }

        val (isSuccess, error) = runBlocking {
            deliveryDelegate.tryToDelivery(mock {}, mock {}, mock {}) { fn.invoke() }
        }

        assertThat(isSuccess, equalTo(false))
        assertThat(error, instanceOf(RuntimeException::class.java))

        verify(fn, times(maxIntents)).invoke()
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
