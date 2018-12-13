package info.digitalpoet.eengine.core

import info.digitalpoet.eengine.core.client.Client
import info.digitalpoet.eengine.core.listener.Listener

/** <!-- Documentation for: info.digitalpoet.eengine.core.Context on 22/11/18 -->
 *
 * Save context elements.
 *
 * @author Aran Moncusí Ramírez
 */
interface Context
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    val clients: List<Client>

    val listeners: List<Listener>

    val properties: ConfigurationProperties

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    fun load()

    fun isInitialized(): Boolean

    fun findClientById(clientId: String): Client?

    fun getOrderedListenersFromChannel(channel: String): List<Listener>?

    //~ Operators ======================================================================================================
}
