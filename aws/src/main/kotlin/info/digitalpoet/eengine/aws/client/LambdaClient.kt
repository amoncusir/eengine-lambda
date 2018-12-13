package info.digitalpoet.eengine.aws.client

import info.digitalpoet.eengine.core.client.Client
import info.digitalpoet.eengine.core.client.Publisher
import info.digitalpoet.eengine.core.client.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.aws.client.LambdaClient on 23/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
class LambdaClient: Client
{
    //~ Constants ======================================================================================================

    companion object
    {
        const val TYPE = "LAMBDA"
    }

    //~ Values =========================================================================================================

    lateinit var arn: String

    override lateinit var name: String

    /**
     * Unique ID for client. Used for authenticate the requests
     */
    override lateinit var id: String

    /**
     * Random Key for verify requests
     */
    override lateinit var key: ByteArray

    /**
     * Type of client
     */
    override val type: String
        get() = TYPE

    /**
     * If client has any subscriber, the subscriber
     */
    override var subscriber: Subscriber? = null

    /**
     * If client has any publisher, the publisher
     */
    override var publisher: Publisher? = null


    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
