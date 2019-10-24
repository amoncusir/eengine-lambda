package info.digitalpoet.eengine.core.postwoman

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import info.digitalpoet.eengine.core.broadcast.BroadcastHandler
import info.digitalpoet.eengine.core.broadcast.BroadcastHandlerDealer
import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.MessageConfiguration
import info.digitalpoet.eengine.core.mockService
import info.digitalpoet.eengine.core.mockSubscriber
import info.digitalpoet.eengine.core.publisher.Publisher
import info.digitalpoet.eengine.core.repository.MessageRepository
import info.digitalpoet.eengine.core.repository.SubscriberRepository
import info.digitalpoet.eengine.core.deliverer.Deliverer
import info.digitalpoet.eengine.core.orchestrator.Orchestrator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/** <!-- Documentation for: info.digitalpoet.eengine.core.postwoman.PostwomanMessageServiceTest on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class PostwomanMessageServiceTest
{
    val defaultMessageConfiguration = MessageConfiguration("type")

    lateinit var deliverer: Deliverer

    lateinit var message: Message

    lateinit var broadcast: BroadcastHandler

    lateinit var messageRepository: MessageRepository

    lateinit var subscriberRepository: SubscriberRepository

    lateinit var broadcastHandlerDealer: BroadcastHandlerDealer

    lateinit var orchestrator: Orchestrator

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    @BeforeEach
    fun prepare()
    {
        deliverer = mock {}

        val services = listOf(mockService("service-one"), mockService("service-two"), mockService("service-three"))
        val subscribers = listOf(
            mockSubscriber("sub-one", deliverer),
            mockSubscriber("sub-two", deliverer),
            mockSubscriber("sub-three", deliverer)
        )

        message = mock {
            on { id } doReturn "id"
            on { channel } doReturn "channel"
            on { content } doReturn byteArrayOf()
            on { publisher } doReturn Publisher("publisher")
        }

        broadcast = mock {
            on { type } doReturn "one"
            on { select(any()) } doReturn subscribers
        }

        messageRepository = mock {
            on { findById(eq("id")) } doReturn message
        }

        subscriberRepository = mock {
            on { findById(eq("id")) } doReturn subscribers.first()
            on { findByChannel(eq("channel")) } doReturn services
            on { getAllSubscribers() } doReturn subscribers
        }

        broadcastHandlerDealer = mock {
            on { instance(any()) } doReturn broadcast
        }

        orchestrator = mock {}
    }

    //~ Tests ==========================================================================================================

    @Test
    fun `Happy path to delivery a message`()
    {
        val postwoman = PostwomanMessageService(
            messageRepository, subscriberRepository, broadcastHandlerDealer, defaultMessageConfiguration, orchestrator
        )

        val message: Message = mock {
            on { id } doReturn "id"
            on { channel } doReturn "channel"
            on { content } doReturn byteArrayOf()
            on { publisher } doReturn Publisher("publisher")
        }

        postwoman.delivery(message) // 4

        //! Correct order to execute:
        //! 1. Save the message
        //! 2. Find the services by channel
        //! 3. Select the services to delivery the message using the BroadcastHandler
        //! 4. If no have any subscriber, throw an error
        //! 5. Put to orchestrator

        verify(messageRepository, atLeastOnce()).save(argThat { this == message }) // 1
        verify(subscriberRepository, atLeastOnce()).findByChannel(argThat { this == "channel" }) // 2
        verify(broadcastHandlerDealer, atLeastOnce()).instance(argThat { this == "type" }) // Before 3
        verify(broadcast, atLeastOnce()).select(argThat { size == 3 }) // 3
        verify(orchestrator, times(3)).put(argThat { this == message }, any(), argThat { broadcastType == "type" }) // 5
    }

    @Test
    fun `Happy path to delivery to specific subscriber a message`()
    {
        val postwoman = PostwomanMessageService(
            messageRepository, subscriberRepository, broadcastHandlerDealer, defaultMessageConfiguration, orchestrator
        )

        val message: Message = mock {
            on { id } doReturn "id"
            on { channel } doReturn "channel"
            on { content } doReturn byteArrayOf()
            on { publisher } doReturn Publisher("publisher")
        }

        postwoman.deliveryTo("id", message) // 4

        verify(messageRepository, atLeastOnce()).save(argThat { this == message }) // 1
        verify(subscriberRepository, atLeastOnce()).findById(argThat { this == "id" }) // 2
        verify(orchestrator, times(1)).put(argThat { this == message }, any(), argThat { broadcastType == "type" }) // 5
    }

    @Test
    fun `No found any subscriber in delivery`()
    {
        broadcast = mock {
            on { type } doReturn "one"
            on { select(any()) } doReturn listOf()
        }

        broadcastHandlerDealer = mock {
            on { instance(any()) } doReturn broadcast
        }

        val postwoman = PostwomanMessageService(
            messageRepository, subscriberRepository, broadcastHandlerDealer, defaultMessageConfiguration, orchestrator
        )

        val message: Message = mock {
            on { id } doReturn "id"
            on { channel } doReturn "channel"
            on { content } doReturn byteArrayOf()
            on { publisher } doReturn Publisher("publisher")
        }

        assertThrows<NotFoundAnySubscriber> { postwoman.delivery(message) }

        verifyZeroInteractions(orchestrator)
    }

    @Test
    fun `No found any subscriber in delivery to id`()
    {
        val postwoman = PostwomanMessageService(
            messageRepository, subscriberRepository, broadcastHandlerDealer, defaultMessageConfiguration, orchestrator
        )

        val message: Message = mock {
            on { id } doReturn "id"
            on { channel } doReturn "channel"
            on { content } doReturn byteArrayOf()
            on { publisher } doReturn Publisher("publisher")
        }

        assertThrows<NotFoundAnySubscriber> { postwoman.deliveryTo("unknown", message) }

        verifyZeroInteractions(orchestrator)
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
