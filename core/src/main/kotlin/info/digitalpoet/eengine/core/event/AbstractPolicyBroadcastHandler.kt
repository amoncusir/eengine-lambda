package info.digitalpoet.eengine.core.event

import info.digitalpoet.eengine.core.listener.Listener
import info.digitalpoet.eengine.core.listener.ListenerProvider
import info.digitalpoet.eengine.core.reactor.Completable
import reactor.core.publisher.Flux
import reactor.core.publisher.toMono

/** <!-- Documentation for: info.digitalpoet.eengine.core.event.AbstractPolicyBroadcastHandler on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
abstract class AbstractPolicyBroadcastHandler(
    private val listenerProvider: ListenerProvider
):
    BroadcastHandler
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    protected fun List<Completable>.prepare(): Flux<Unit>
    {
        return Flux.merge(map { polices(it) })
    }

    protected abstract fun polices(stream: Completable): Completable

    open fun initStream(event: Event, listeners: List<Listener>): List<Completable> =
        listeners.map { it.send(event) }

    //~ Methods ========================================================================================================

    @Throws(EventBroadcastException::class)
    override fun broadcast(event: Event): Completable
    {
        val listeners = listenerProvider.getOrderedListenersFromChannel(event.channel) ?:
                throw EventBroadcastException(event, "Not exist any listener for channel: ${event.channel}")

        return initStream(event, listeners)
            .prepare()
            .toMono()
    }

    //~ Operators ======================================================================================================
}
