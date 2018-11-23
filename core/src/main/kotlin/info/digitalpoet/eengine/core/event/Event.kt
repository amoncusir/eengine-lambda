package info.digitalpoet.eengine.core.event

import java.util.UUID

/** <!-- Documentation for: info.digitalpoet.eengine.core.event.Event on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
class Event()
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    lateinit var id: String

    lateinit var channel: String

    var data: ByteArray? = null

    //~ Constructors ===================================================================================================

    constructor(id: String, channel: String, data: ByteArray? = null): this()
    {
        this.id = id
        this.channel = channel
        this.data = data
    }

    constructor(channel: String, data: ByteArray? = null): this(UUID.randomUUID().toString(), channel, data)

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
