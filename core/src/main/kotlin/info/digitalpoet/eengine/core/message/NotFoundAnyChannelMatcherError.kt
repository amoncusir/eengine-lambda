package info.digitalpoet.eengine.core.message

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.NotFoundAnyChannelMatcher on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class NotFoundAnyChannelMatcherError(channel: String): Throwable(
    "Not found any ChannelMatcher for channelDescriptor: $channel"
)
