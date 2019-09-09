package info.digitalpoet.eengine.core.repository

import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import info.digitalpoet.eengine.core.message.ChannelMatcher
import info.digitalpoet.eengine.core.mockService
import info.digitalpoet.eengine.core.mockSubscriber
import info.digitalpoet.eengine.core.subscriber.Service
import info.digitalpoet.eengine.core.subscriber.Subscriber
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.internal.util.MockUtil

/** <!-- Documentation for: info.digitalpoet.eengine.core.repository.OnCachedSubscriberRepositoryTest on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class OnCachedSubscriberRepositoryTest
{
    lateinit var mockedRepo: SubscriberRepository

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    @BeforeEach
    fun prepare()
    {
        val subscribers = listOf(mockSubscriber("sub-one"), mockSubscriber("sub-two"), mockSubscriber("sub-three"))
        val services = listOf(mockService("service-one"), mockService("service-two"), mockService("service-three"))

        mockedRepo = mock {
            on { findByChannel(eq("channel")) } doReturn services
            on { getAllSubscribers() } doReturn subscribers
        }
    }

    //~ Tests ==========================================================================================================

    @Test
    fun `Test wrapped methods`()
    {
        val repository: SubscriberRepository = OnCachedSubscriberRepository(mockedRepo)

        // Save method

        repository.save(mock {})

        verify(mockedRepo, times(1)).save(argThat { MockUtil.isMock(this) })

        // findByChannel method with known channel

        val channelSubscriber = repository.findByChannel("sub-one")

        verify(mockedRepo, times(1)).getAllSubscribers()

        assertEquals(1, channelSubscriber.size)
        assertEquals("service-sub-one", channelSubscriber.first().id) //Service.id
        assertEquals("sub-one", channelSubscriber.first().subscribers.first().id) //Subscriber.id

        // findByChannel method with unknown channel

        val unknownSubscriber = repository.findByChannel("unknown")

        verify(mockedRepo, times(1)).getAllSubscribers()

        assert(unknownSubscriber.isEmpty())

        // getAllSubscribers method

        val subscribers = repository.getAllSubscribers()

        verify(mockedRepo, times(1)).getAllSubscribers()

        assertEquals(3, subscribers.size)

        // deleteSubscriber method

        repository.deleteSubscriber("id")

        verify(mockedRepo, times(1)).deleteSubscriber(argThat { this == "id" })
    }

    @Test
    fun `Test refresh cache`()
    {
        val repository: SubscriberRepository = OnCachedSubscriberRepository(mockedRepo, 1)

        repository.getAllSubscribers()

        verify(mockedRepo, times(1)).getAllSubscribers()

        Thread.sleep(10)

        repository.getAllSubscribers()

        verify(mockedRepo, times(2)).getAllSubscribers()
    }

    @Test
    fun `Test hard consistency`()
    {
        val repository: SubscriberRepository = OnCachedSubscriberRepository(mockedRepo, hardConsistency = true)

        repository.getAllSubscribers() // Load Cache

        repository.deleteSubscriber("sub-one")

        val subscribers = repository.getAllSubscribers()

        assertEquals(2, subscribers.size)
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================

}
