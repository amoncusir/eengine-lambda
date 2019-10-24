package info.digitalpoet.eengine.core.matcher

/** <!-- Documentation for: info.digitalpoet.eengine.core.matcher.ChannelMatcherFactory on 29/8/19 -->
 *
 * Factory implementation for class `ChannelMatcher`
 *
 * @author Aran Moncusí Ramírez
 */
interface ChannelMatcherFactory
{
    val type: String

    fun instance(data: Map<String, Any?>): ChannelMatcher
}
