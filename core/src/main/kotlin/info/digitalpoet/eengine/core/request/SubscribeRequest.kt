package info.digitalpoet.eengine.core.request

/** <!-- Documentation for: info.digitalpoet.eengine.core.request.SubscribeRequest on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface SubscribeRequest: AuthenticatedRequest
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    val type: String

    val method: String

    val metadata: Map<String, String>

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
