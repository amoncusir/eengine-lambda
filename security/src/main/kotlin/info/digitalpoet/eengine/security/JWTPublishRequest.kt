package info.digitalpoet.eengine.security

import info.digitalpoet.eengine.core.event.Event
import info.digitalpoet.eengine.core.request.PublishRequest

/** <!-- Documentation for: info.digitalpoet.eengine.security.JWTPublishRequest on 11/12/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
abstract class JWTPublishRequest(
    override val event: Event,
    channel: String,
    token: String
):
    JWTAuthenticationRequest(channel, token),
        PublishRequest
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    override val channel: String
        get() = event.channel

    //~ Operators ======================================================================================================
}
