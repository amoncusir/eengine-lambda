package info.digitalpoet.eengine.core.request

/** <!-- Documentation for: info.digitalpoet.eengine.core.request.SimpleAuthenticatedRequest on 11/12/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class SimpleAuthenticatedRequest(
    override val clientId: String,
    override val channel: String,
    override val metadata: Map<String, String>?,
    val secret: ByteArray
):
    AuthenticatedRequest
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    override fun matchKey(key: ByteArray): Boolean = secret.contentEquals(key)

    //~ Operators ======================================================================================================
}
