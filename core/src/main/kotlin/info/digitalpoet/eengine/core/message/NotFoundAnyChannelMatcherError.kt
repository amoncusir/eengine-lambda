package info.digitalpoet.eengine.core.message

import info.digitalpoet.eengine.core.NoRetryError

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.NotFoundAnyChannelMatcher on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class NotFoundAnyChannelMatcherError(channel: String): NoRetryError(
    "Not found any ChannelMatcher for channelDescriptor: $channel", null
)
