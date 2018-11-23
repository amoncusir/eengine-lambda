package info.digitalpoet.eengine.core.request

import info.digitalpoet.eengine.core.event.Event

/** <!-- Documentation for: info.digitalpoet.eengine.core.request.PublishRequest on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface PublishRequest: AuthenticatedRequest
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    val event: Event

    override val channel: String
        get() = event.channel

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
