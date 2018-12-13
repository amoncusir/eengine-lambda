package info.digitalpoet.eengine.core.listener

import info.digitalpoet.eengine.core.client.Client
import info.digitalpoet.eengine.core.request.SubscribeRequest

/** <!-- Documentation for: info.digitalpoet.eengine.core.listener.ListenerBuilder on 23/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface ListenerBuilder
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    fun create(client: Client): Listener

    fun create(clients: List<Client>): List<Listener> = clients.map { create(it) }

    fun update(subscribeRequest: SubscribeRequest, listener: Listener)

    //~ Operators ======================================================================================================
}
