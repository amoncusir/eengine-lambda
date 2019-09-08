package info.digitalpoet.eengine.core.message

import javax.json.JsonObject

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.StringChannelMatcher on 8/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class StringChannelMatcher(private val keyword: String): ChannelMatcher
{
    companion object
    {
        class Factory: ChannelMatcherFactory
        {
            override val type = "string"

            override fun instance(data: JsonObject): ChannelMatcher
            {
                return StringChannelMatcher(data.getString("keyword"))
            }
        }
    }

    override fun match(channel: String): Boolean = channel == keyword
}
