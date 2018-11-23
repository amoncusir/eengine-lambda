package info.digitalpoet.eengine.core.listener

/** <!-- Documentation for: info.digitalpoet.eengine.core.chennel.ListenerProvider on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface ListenerProvider
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    fun getOrderedListenersFromChannel(channel: String): List<Listener>?

    fun add(listener: Listener)

    fun remove(listenerId: String)

    fun getListener(listenerId: String): Listener

    fun existListener(listenerId: String): Boolean

    fun updateLister(listenerId: String, listener: Listener)

    //~ Operators ======================================================================================================
}
