package info.digitalpoet.eengine.security

import info.digitalpoet.eengine.core.request.SubscribeRequest

/** <!-- Documentation for: info.digitalpoet.eengine.security.JWTSubscribeRequest on 11/12/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
abstract class JWTSubscribeRequest(
    override val type: String,
    override val protocol: String,
    channel: String,
    token: String
):
    JWTAuthenticationRequest(channel, token),
        SubscribeRequest
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
