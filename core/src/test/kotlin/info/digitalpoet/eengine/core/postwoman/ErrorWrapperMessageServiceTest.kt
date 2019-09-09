package info.digitalpoet.eengine.core.postwoman

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import info.digitalpoet.eengine.core.NoRetryError
import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.MessageConfiguration
import info.digitalpoet.eengine.core.publisher.Publisher
import info.digitalpoet.eengine.core.service.MessageService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/** <!-- Documentation for: info.digitalpoet.eengine.core.postwoman.ErrorWrapperMessageServiceTest on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class ErrorWrapperMessageServiceTest
{
    lateinit var message: Message

    val defaultConfiguration = MessageConfiguration("type", 3, 3, "error")

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    @BeforeEach
    fun prepare()
    {
        message = mock {
            on { id } doReturn "id"
            on { channel } doReturn "channel"
            on { content } doReturn byteArrayOf()
            on { publisher } doReturn Publisher("publisher")
        }
    }

    //~ Tests ==========================================================================================================

    @Test
    fun `delivery without errors`()
    {
        val delegator: MessageService = mock {}
        val wrapper = ErrorWrapperMessageService(delegator, defaultConfiguration)

        wrapper.delivery(message)

        verify(delegator, times(1)).delivery(argThat { id == "id" && channel == "channel" })
    }

    @Test
    fun `delivery with external error`()
    {
        val delegator: MessageService = mock { on { delivery(any()) } doThrow RuntimeException("Test Error!") }
        val wrapper = ErrorWrapperMessageService(delegator, defaultConfiguration)

        assertThrows<RuntimeException> { wrapper.delivery(message) }

        inOrder(delegator) {
            verify(delegator, times(3)).delivery(argThat { id == "id" && channel == "channel" })
            verify(delegator, times(1)).delivery(argThat { channel == defaultConfiguration.errorChannel!! })
        }
    }

    @Test
    fun `delivery with external error but success send to error chanel`()
    {
        val delegator: MessageService = mock {
            on { delivery(any()) } doAnswer {
                if ((it.getArgument(0) as Message).channel == defaultConfiguration.errorChannel)
                    Unit
                else
                    throw RuntimeException("Error")
            }
        }

        val wrapper = ErrorWrapperMessageService(delegator, defaultConfiguration)

        wrapper.delivery(message)

        inOrder(delegator) {
            verify(delegator, times(3)).delivery(argThat { id == "id" && channel == "channel" })
            verify(delegator, times(1)).delivery(argThat { channel == defaultConfiguration.errorChannel!! })
        }
    }

    @Test
    fun `delivery with NoRetryError error`()
    {
        val delegator: MessageService = mock { on { delivery(any()) } doThrow NoRetryError("Test Error!") }
        val wrapper = ErrorWrapperMessageService(delegator, defaultConfiguration)

        assertThrows<RuntimeException> { wrapper.delivery(message) }

        inOrder(delegator) {
            verify(delegator, times(1)).delivery(argThat { id == "id" && channel == "channel" })
            verify(delegator, times(1)).delivery(argThat { channel == defaultConfiguration.errorChannel!! })
        }
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
