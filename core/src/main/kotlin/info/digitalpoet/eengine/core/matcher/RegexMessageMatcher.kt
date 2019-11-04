package info.digitalpoet.eengine.core.matcher

import info.digitalpoet.eengine.core.message.Message

/** <!-- Documentation for: info.digitalpoet.eengine.core.matcher.RegexChannelMatcher on 8/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class RegexMessageMatcher(private val regex: Regex): MessageMatcher
{
    companion object
    {
        const val TYPE = "regex"
    }

    class Factory: MessageMatcherFactory
    {
        override val type = TYPE

        @Suppress("UNCHECKED_CAST")
        override fun instance(data: Map<String, Any?>): MessageMatcher
        {
            val regexFormula = data["regex"] as String
            val options = getOptions(data.getOrDefault("options", listOf<String>()) as List<String>)

            return RegexMessageMatcher(Regex(regexFormula, options))
        }

        private fun getOptions(options: List<String>): Set<RegexOption>
        {
            return options
                .map { RegexOption.valueOf(it) }
                .toSet()
        }
    }

    override fun match(message: Message): Boolean = regex.matches(message.channel)
}
