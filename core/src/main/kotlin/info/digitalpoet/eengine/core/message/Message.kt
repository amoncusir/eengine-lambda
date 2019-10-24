package info.digitalpoet.eengine.core.message

import info.digitalpoet.eengine.core.publisher.Publisher

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.Message on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
data class Message(
    val id: String,
    val channel: String,
    val content: ByteArray,
    val publisher: Publisher,
    val configuration: MessageConfiguration?
)
{
    override fun equals(other: Any?): Boolean
    {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Message

        if (id != other.id) return false
        if (channel != other.channel) return false

        return true
    }

    override fun hashCode(): Int
    {
        var result = id.hashCode()
        result = 31 * result + channel.hashCode()
        return result
    }
}
