package info.digitalpoet.eengine.core.listener

import info.digitalpoet.eengine.core.event.Event
import info.digitalpoet.eengine.core.reactor.Completable

/** <!-- Documentation for: info.digitalpoet.eengine.core.chennel.Listener on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface Listener
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    val id: String

    val priority: Int

    val clientId: String

    val type: String

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    fun isListening(channel: String): Boolean

    fun send(event: Event): Completable

    //~ Operators ======================================================================================================
}
