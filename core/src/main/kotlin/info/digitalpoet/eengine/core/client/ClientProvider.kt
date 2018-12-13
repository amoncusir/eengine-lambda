package info.digitalpoet.eengine.core.client

/** <!-- Documentation for: info.digitalpoet.eengine.core.client.ClientProvider on 23/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface ClientProvider
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    /**
     * Load clients from source.
     *
     * @return List<Client> Generated [Client] list from sources.
     */
    fun loadClients(): List<Client>

    //~ Operators ======================================================================================================
}
