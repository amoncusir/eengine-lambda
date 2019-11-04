package info.digitalpoet.eengine.core.orchestrator

import info.digitalpoet.eengine.core.deliverer.TryDeliveryDelegate
import info.digitalpoet.eengine.core.message.ErrorMessageDelegate
import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.MessageConfiguration
import info.digitalpoet.eengine.core.subscriber.Subscriber
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/** <!-- Documentation for: info.digitalpoet.eengine.core.orchestrator.ParallelFiFoQueueOrchestrator on 12/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class CoroutineOrchestrator(
    private val errorMessageDelegate: ErrorMessageDelegate,
    private val tryDeliveryDelegate: TryDeliveryDelegate,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
):
    Orchestrator
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override fun put(message: Message, subscriber: Subscriber, configuration: MessageConfiguration)
    {
        scope.launch {
            process(message, subscriber, configuration)
        }
    }

    //~ Methods ========================================================================================================

    private suspend fun process(message: Message, subscriber: Subscriber, configuration: MessageConfiguration)
    {
        val (isSuccess, error) = tryDeliveryDelegate.tryToDelivery(message, subscriber, configuration) {
            subscriber.deliverer.delivery(message)
        }

        if (!isSuccess)
            errorMessageDelegate.process(message, subscriber, configuration, error!!)
    }

    //~ Operators ======================================================================================================
}
