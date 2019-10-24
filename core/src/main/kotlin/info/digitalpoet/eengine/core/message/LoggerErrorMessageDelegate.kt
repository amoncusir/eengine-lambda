package info.digitalpoet.eengine.core.message

import info.digitalpoet.eengine.core.subscriber.Subscriber
import mu.KotlinLogging

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.LoggerErrorMessageDelegate on 13/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class LoggerErrorMessageDelegate: ErrorMessageDelegate
{
    private val logger = KotlinLogging.logger {}

    override fun process(
        message: Message, subscriber: Subscriber, configuration: MessageConfiguration, error: Throwable
    )
    {
        logger.error(error) { "Error to delivery message: $message, $subscriber, $configuration" }
    }
}
