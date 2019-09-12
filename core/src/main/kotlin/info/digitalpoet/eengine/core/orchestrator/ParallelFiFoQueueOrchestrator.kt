package info.digitalpoet.eengine.core.orchestrator

import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.MessageConfiguration
import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.orchestrator.ParallelFiFoQueueOrchestrator on 12/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class ParallelFiFoQueueOrchestrator: Orchestrator
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override fun put(message: Message, subscriber: Subscriber, configuration: MessageConfiguration)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
