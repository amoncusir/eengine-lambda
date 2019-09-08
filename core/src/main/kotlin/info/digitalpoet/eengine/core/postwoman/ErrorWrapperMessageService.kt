package info.digitalpoet.eengine.core.postwoman

import info.digitalpoet.eengine.core.NoRetryError
import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.MessageConfiguration
import info.digitalpoet.eengine.core.publisher.Publisher
import info.digitalpoet.eengine.core.service.MessageService
import mu.KotlinLogging
import java.util.UUID
import javax.json.Json

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
        val configuration = message.configuration ?: defaultMessageConfiguration
        val maxIntents = configuration.errorIntents
        var intents = 0
        var hasError = false

        do
        {
            try
            {
                delegator.delivery(message);
            }
            catch (error: Throwable)
            {
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
                delegator.delivery(errorMessage);
            }
            catch (error: Throwable)
            {
                logger.error(error) { "Error when try to send error message: ${errorMessage.id}" }
            }
        }
    }

    protected open fun createErrorMessage(message: Message, configuration: MessageConfiguration): Message
    {
        return Message(
            UUID.randomUUID().toString(),
            configuration.errorChannel,
            message.data,
            createPublisher(message, message.publisher),
            configuration
        )
    }

    protected open fun createPublisher(message: Message, publisher: Publisher): Publisher
    {
        val publisherData = Json.createObjectBuilder()
            .add("originalName", publisher.name)
            .add("originalMetadata", publisher.metadata)
            .add("originalMessageId", message.id)
            .add("originalMessageChannel", message.channel)
            .build()

        return Publisher("ErrorWrapperMessageService", publisherData)
    }
}
