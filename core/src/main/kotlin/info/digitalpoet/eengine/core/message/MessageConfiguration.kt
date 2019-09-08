package info.digitalpoet.eengine.core.message

import javax.json.JsonObject

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.MessageConfiguration on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class MessageConfiguration(
    val broadcastType: String,
    val replyIntents: Int = 3,
    val errorIntents: Int = 0,
    val errorChannel: String = "eengine/error",
    val metadata: JsonObject? = null
)
