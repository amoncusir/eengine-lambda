package info.digitalpoet.eengine.core.listener

import info.digitalpoet.eengine.core.client.Client
import info.digitalpoet.eengine.core.request.SubscribeRequest

/** <!-- Documentation for: info.digitalpoet.eengine.core.listener.DelegateListenerBuilder on 23/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class DelegateListenerBuilder(
    private val specializedBuilders: List<SpecializedListenerBuilder>
):
    ListenerBuilder
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    @Throws(ListenerException::class)
    override fun update(subscribeRequest: SubscribeRequest, listener: Listener)
    {
        val builder = specializedBuilders.firstOrNull { it.supportRequest(subscribeRequest) } ?:
                throw ListenerException("Can't find any ListenerBuilder for subscriber type: ${subscribeRequest.type}")

        builder.update(subscribeRequest, listener)
    }

    override fun create(client: Client): Listener
    {
        val builder = specializedBuilders.firstOrNull { it.supportsClient(client) } ?:
                throw ListenerException("Can't find any ListenerBuilder for client id: ${client.id}")

        return builder.create(client)
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
