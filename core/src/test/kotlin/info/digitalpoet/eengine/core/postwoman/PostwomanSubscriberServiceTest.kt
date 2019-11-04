package info.digitalpoet.eengine.core.postwoman

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import info.digitalpoet.eengine.core.deliverer.Deliverer
import info.digitalpoet.eengine.core.deliverer.DelivererFactoryDealer
import info.digitalpoet.eengine.core.matcher.MessageMatcher
import info.digitalpoet.eengine.core.matcher.MessageMatcherFactoryDealer
import info.digitalpoet.eengine.core.repository.SubscriberRepository
import info.digitalpoet.eengine.core.service.SubscriberPetition
import info.digitalpoet.eengine.core.subscriber.Subscriber
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.internal.util.MockUtil

/** <!-- Documentation for: info.digitalpoet.eengine.core.postwoman.PostwomanSubscriberServiceTest on 26/10/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class PostwomanSubscriberServiceTest
{
    lateinit var subscriberRepository: SubscriberRepository

    lateinit var chanelMatcherFactoryDealer: MessageMatcherFactoryDealer

    lateinit var delivererFactoryDealer: DelivererFactoryDealer

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    @BeforeEach
    fun prepare()
    {
        val subscriber: Subscriber = mock {
            on { id } doReturn "id"
        }

        val matcher: MessageMatcher = mock {}

        val deliverer: Deliverer = mock {}

        subscriberRepository = mock {
            on { getAllSubscribers() } doReturn listOf(subscriber)
        }

        chanelMatcherFactoryDealer = mock {
            on { instance(any(), any()) } doReturn matcher
        }

        delivererFactoryDealer = mock {
            on { instance(any(), any()) } doReturn deliverer
        }
    }

    //~ Tests ==========================================================================================================

    @Test
    fun `createSubscriber - save in repository , create factory and deliverer`()
    {
        val service = PostwomanSubscriberService(
            subscriberRepository, chanelMatcherFactoryDealer, delivererFactoryDealer
        )

        service.createSubscriber(SubscriberPetition(
            "id",
            "serviceId",
            "channel",
            mapOf(),
            "type",
            mapOf()
        ))

        verify(subscriberRepository, times(1)).save(argThat {
            serviceId == "serviceId" && MockUtil.isMock(messageMatcher) && MockUtil.isMock(deliverer)
        })
    }

    @Test
    fun `getAllSubscribers - from repository`()
    {
        val service = PostwomanSubscriberService(
            subscriberRepository, chanelMatcherFactoryDealer, delivererFactoryDealer
        )

        val subscribers = service.getAllSubscribers()

        assertThat(subscribers, hasSize(1))
        assertThat(subscribers.first().id, equalTo("id"))
    }

    @Test
    fun `removeSubscriber - from repository`()
    {
        val service = PostwomanSubscriberService(
            subscriberRepository, chanelMatcherFactoryDealer, delivererFactoryDealer
        )

        service.removeSubscriber("id")

        verify(subscriberRepository, times(1)).deleteSubscriber("id")
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
