package info.digitalpoet.eengine.core.client

/** <!-- Documentation for: info.digitalpoet.eengine.core.client.Client on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface Client
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    /**
     * Unique ID for client. Used for authenticate the requests
     */
    val id: String

    /**
     * Random Key for verify JWT token in authenticated requests
     */
    val key: ByteArray

    /**
     * Type of client
     */
    val type: String

    /**
     * If client has any subscriber, the subscriber
     */
    val subscriber: Subscriber?

    /**
     * If client has any publisher, the publisher
     */
    val publisher: Publisher?

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
