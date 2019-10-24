package info.digitalpoet.eengine.core.message

import info.digitalpoet.eengine.core.deliverer.Deliverer
import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.ErrorMessageDelegate on 13/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface ErrorMessageDelegate
{
    fun process(message: Message, subscriber: Subscriber, configuration: MessageConfiguration, error: Throwable)
}
