package info.digitalpoet.eengine.core.message

import info.digitalpoet.eengine.core.publisher.Publisher

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.Message on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class Message(
    val id: String,
    val channel: String,
    val data: ByteArray,
    val publisher: Publisher,
    val configuration: MessageConfiguration?
)
