package info.digitalpoet.eengine.core.client

/** <!-- Documentation for: info.digitalpoet.eengine.core.client.SimpleClient on 12/12/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class SimpleClient(
    override val name: String,
    override val id: String,
    override val key: ByteArray,
    override val type: String,
    override val subscriber: Subscriber?,
    override val publisher: Publisher?
):
    Client
