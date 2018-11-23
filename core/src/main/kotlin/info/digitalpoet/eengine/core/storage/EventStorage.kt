package info.digitalpoet.eengine.core.storage

import info.digitalpoet.eengine.core.event.Event

/** <!-- Documentation for: info.digitalpoet.eengine.core.storage.EventStorage on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface EventStorage
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    fun saveEvent(event: Event)

    fun findEvent(eventId: String): Event

    //~ Operators ======================================================================================================
}
