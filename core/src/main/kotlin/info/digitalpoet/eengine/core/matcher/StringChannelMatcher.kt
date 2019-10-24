package info.digitalpoet.eengine.core.matcher

/** <!-- Documentation for: info.digitalpoet.eengine.core.matcher.StringChannelMatcher on 8/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class StringChannelMatcher(private val keyword: String, private val ignoreCase: Boolean = false):
    ChannelMatcher
{
    companion object
    {
        const val TYPE = "string"

    }

    class Factory: ChannelMatcherFactory
    {
        override val type = TYPE

        override fun instance(data: Map<String, Any?>): ChannelMatcher
        {
            val keyword = data["keyword"] as String
            val ignoreCase = data["ignoreCase"] as? Boolean

            return ignoreCase?.let {
                StringChannelMatcher(keyword, it)
            } ?: StringChannelMatcher(keyword)
        }
    }

    override fun match(channel: String): Boolean = if (ignoreCase)
        channel.toLowerCase() == keyword.toLowerCase()
    else
        channel == keyword
}
