package info.digitalpoet.eengine.core.matcher

import info.digitalpoet.eengine.core.message.Message

/** <!-- Documentation for: info.digitalpoet.eengine.core.matcher.StringChannelMatcher on 8/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class StringMessageMatcher(private val keyword: String, private val ignoreCase: Boolean = false):
    MessageMatcher
{
    companion object
    {
        const val TYPE = "string"
    }

    class Factory: MessageMatcherFactory
    {
        override val type = TYPE

        override fun instance(data: Map<String, Any?>): MessageMatcher
        {
            val keyword = data["keyword"] as String
            val ignoreCase = data["ignoreCase"] as? Boolean

            return ignoreCase?.let {
                StringMessageMatcher(keyword, it)
            } ?: StringMessageMatcher(keyword)
        }
    }

    override fun match(message: Message): Boolean = if (ignoreCase)
        message.channel.toLowerCase() == keyword.toLowerCase()
    else
        message.channel == keyword
}
