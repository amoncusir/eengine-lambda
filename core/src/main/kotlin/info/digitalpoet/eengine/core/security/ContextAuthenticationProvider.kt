package info.digitalpoet.eengine.core.security

import info.digitalpoet.eengine.core.Context
import info.digitalpoet.eengine.core.NotFindAnyInstance
import info.digitalpoet.eengine.core.client.Client
import info.digitalpoet.eengine.core.request.AuthenticatedRequest

/** <!-- Documentation for: info.digitalpoet.eengine.core.security.ContextAuthenticationProvider on 23/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class ContextAuthenticationProvider(
    private val context: Context
):
    RequestAuthenticationProvider
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override fun approveRequest(request: AuthenticatedRequest): Boolean
    {
        val client = context.findClientById(request.clientId) ?:
                throw NotFindAnyInstance(Client::class, "Can't find client by id: ${request.clientId} in request")

        return request.matchKey(client.key)
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
