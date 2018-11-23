package info.digitalpoet.eengine.core.listener

import info.digitalpoet.eengine.core.client.Client
import info.digitalpoet.eengine.core.request.SubscribeRequest

/** <!-- Documentation for: info.digitalpoet.eengine.core.listener.SpecializedListenerBuilder on 23/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface SpecializedListenerBuilder: ListenerBuilder
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    fun supportRequest(subscribeRequest: SubscribeRequest): Boolean

    fun supportsClient(client: Client): Boolean

    //~ Operators ======================================================================================================
}
