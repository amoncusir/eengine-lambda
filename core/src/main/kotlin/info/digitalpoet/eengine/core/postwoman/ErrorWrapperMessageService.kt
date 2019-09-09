package info.digitalpoet.eengine.core.postwoman

import info.digitalpoet.eengine.core.NoRetryError
import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.MessageConfiguration
import info.digitalpoet.eengine.core.publisher.Publisher
import info.digitalpoet.eengine.core.service.MessageService
import mu.KotlinLogging
import java.util.UUID

/** <!-- Documentation for: info.digitalpoet.eengine.core.postwoman.ErrorWrapperMessageService on 8/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class ErrorWrapperMessageService(
    private val delegator: MessageService,
    private val defaultMessageConfiguration: MessageConfiguration
):
    MessageService
{
    private val logger = KotlinLogging.logger {}

    override fun delivery(message: Message)
    {
        val configuration = MessageConfiguration.merge(message.configuration, defaultMessageConfiguration)
        val maxIntents = configuration.errorIntents!!
        var intents = 0
        var hasError = false

        do
        {
            try
            {
                delegator.delivery(message)
                break
            }
            catch (error: Throwable)
            {
                logger.warn { "Error when try to send message: ${message.id}" }

                hasError = true

                if (error is NoRetryError)
                    break
            }
            finally
            {
                ++intents
            }
        } while (intents < maxIntents)

        if (hasError)
        {
            val errorMessage = createErrorMessage(message, configuration)

            try
            {
                delegator.delivery(errorMessage)
            }
            catch (error: Throwable)
            {
                logger.error(error) { "Error when try to send error message: ${errorMessage.id}" }

                throw error
            }
        }
    }

    protected open fun createErrorMessage(message: Message, configuration: MessageConfiguration): Message
    {
        return Message(
            UUID.randomUUID().toString(),
            configuration.errorChannel!!,
            message.content,
            createPublisher(message, message.publisher),
            configuration
        )
    }

    protected open fun createPublisher(message: Message, publisher: Publisher): Publisher
    {
        val publisherData = mapOf(
            "originalName" to publisher.name,
            "originalMetadata" to publisher.metadata,
            "originalMessageId" to message.id,
            "originalMessageChannel" to message.channel
        )

        return Publisher("ErrorWrapperMessageService", publisherData)
    }
}
