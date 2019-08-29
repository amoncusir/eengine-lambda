package info.digitalpoet.eengine.core.message

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.ChannelMatcherFactory on 29/8/19 -->
 *
 * Factory implementation for class `ChannelMatcher`
 *
 * @author Aran Moncusí Ramírez
 */
interface ChannelMatcherFactory
{
    fun instance(channelDescriptor: String): ChannelMatcher?
}
