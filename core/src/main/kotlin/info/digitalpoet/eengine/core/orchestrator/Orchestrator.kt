package info.digitalpoet.eengine.core.orchestrator

import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.MessageConfiguration
import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.deliverer.DelivererOrchestrator on 12/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface Orchestrator
{
    fun put(message: Message, subscriber: Subscriber, configuration: MessageConfiguration)
}
