package info.digitalpoet.eengine.core.repository

import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import info.digitalpoet.eengine.core.mockService
import info.digitalpoet.eengine.core.mockSubscriber
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

        mockedRepo = mock {
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
