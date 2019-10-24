package info.digitalpoet.eengine.core.deliverer

import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.MessageConfiguration
import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.deliverer.TryDeliveryDelegate on 13/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface TryDeliveryDelegate
{
    suspend fun tryToDelivery(
        message: Message,
        subscriber: Subscriber,
        configuration: MessageConfiguration,
        fn: suspend () -> Unit
    ):
            Pair<Boolean, Throwable?>
}
