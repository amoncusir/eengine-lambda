package info.digitalpoet.eengine.core.message

import javax.json.JsonArray
import javax.json.JsonObject
import javax.json.JsonString

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.RegexChannelMatcher on 8/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class RegexChannelMatcher(private val regex: Regex): ChannelMatcher
{
    companion object
    {
        class Factory: ChannelMatcherFactory
        {
            override val type = "string"

            override fun instance(data: JsonObject): ChannelMatcher
            {
                val regexFormula = data.getString("regex")
                val options = getOptions(data.getJsonArray("options"))

                return RegexChannelMatcher(Regex(regexFormula, options))
            }

            private fun getOptions(options: JsonArray): Set<RegexOption>
            {
                return options.getValuesAs(JsonString::class.java)
                    .map { it.string }
                    .map { RegexOption.valueOf(it) }
                    .toSet()
            }
        }
    }

    override fun match(channel: String): Boolean = regex.matches(channel)
}
