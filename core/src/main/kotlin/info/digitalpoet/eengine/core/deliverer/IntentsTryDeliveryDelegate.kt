package info.digitalpoet.eengine.core.deliverer

import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.MessageConfiguration
import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.deliverer.IntentsTryDeliveryDelegate on 13/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class IntentsTryDeliveryDelegate(private val maxIntents: Int = 3): TryDeliveryDelegate
{
    override suspend fun tryToDelivery(
        message: Message, subscriber: Subscriber, configuration: MessageConfiguration, fn: suspend () -> Unit
    ):
            Pair<Boolean, Throwable?>
    {
        var intents = 0
        var error: Throwable? = null

        do
        {
            try
            {
                fn.invoke()
                break
            }
            catch (e: Throwable)
            {
                error = e
            }

            intents++

        } while (intents < maxIntents)

        return (error == null) to error
    }
}
