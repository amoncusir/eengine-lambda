package info.digitalpoet.eengine.core.event

import info.digitalpoet.eengine.core.listener.BroadcastListenerException
import info.digitalpoet.eengine.core.listener.ListenerProvider
import info.digitalpoet.eengine.core.reactor.Completable

/** <!-- Documentation for: info.digitalpoet.eengine.core.event.ErrorDelegateBroadcastHandler on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
class ErrorDelegateBroadcastHandler(
    private val listenerProvider: ListenerProvider,
    private val errorHandler: EventErrorHandler
):
    AbstractPolicyBroadcastHandler(listenerProvider)
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    val retries: Long = 2L

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override fun polices(stream: Completable): Completable
    {
        return stream
            .retry(retries)
            .doOnError(BroadcastListenerException::class.java) { errorHandler.manageEvent(it.event, it.listenerId) }
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
