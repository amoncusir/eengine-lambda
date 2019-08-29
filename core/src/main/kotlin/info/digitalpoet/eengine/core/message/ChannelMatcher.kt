package info.digitalpoet.eengine.core.message

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.ChannelMatcher on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface ChannelMatcher
{
    fun match(channel: String): Boolean
}
