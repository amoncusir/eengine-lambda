package info.digitalpoet.eengine.core.security

import info.digitalpoet.eengine.core.request.AuthenticatedRequest

/** <!-- Documentation for: info.digitalpoet.eengine.core.security.RequestAuthenticationProvider on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface RequestAuthenticationProvider
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    fun approveRequest(request: AuthenticatedRequest): Boolean

    //~ Operators ======================================================================================================
}
