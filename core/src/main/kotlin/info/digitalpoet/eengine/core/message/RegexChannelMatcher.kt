package info.digitalpoet.eengine.core.message

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.RegexChannelMatcher on 8/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class RegexChannelMatcher(private val regex: Regex): ChannelMatcher
{
    companion object
    {
        const val TYPE = "regex"
    }

    class Factory: ChannelMatcherFactory
    {
        override val type = TYPE

        @Suppress("UNCHECKED_CAST")
        override fun instance(data: Map<String, Any?>): ChannelMatcher
        {
            val regexFormula = data["regex"] as String
            val options = getOptions(data.getOrDefault("options", listOf<String>()) as List<String>)

            return RegexChannelMatcher(Regex(regexFormula, options))
        }

        private fun getOptions(options: List<String>): Set<RegexOption>
        {
            return options
                .map { RegexOption.valueOf(it) }
                .toSet()
        }
    }

    override fun match(channel: String): Boolean = regex.matches(channel)
}
