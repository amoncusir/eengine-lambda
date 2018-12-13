package info.digitalpoet.eengine.core.request

/** <!-- Documentation for: info.digitalpoet.eengine.core.request.AuthenticatedRequest on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface AuthenticatedRequest
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    val clientId: String

    val channel: String

    val metadata: Map<String, String>?

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    fun matchKey(key: ByteArray): Boolean

    //~ Operators ======================================================================================================
}
