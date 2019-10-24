package info.digitalpoet.eengine.core.matcher

import info.digitalpoet.eengine.core.EEngineError

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.NotFoundAnyChannelMatcher on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class NotFoundAnyChannelMatcherError(channel: String): EEngineError(
    "Not found any ChannelMatcher for channelDescriptor: $channel", null
)
