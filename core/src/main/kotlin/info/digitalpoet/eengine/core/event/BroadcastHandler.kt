package info.digitalpoet.eengine.core.event

import info.digitalpoet.eengine.core.reactor.Completable

/** <!-- Documentation for: info.digitalpoet.eengine.core.event.BroadcastHandler on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface BroadcastHandler
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    fun broadcast(event: Event): Completable

    //~ Operators ======================================================================================================
}
