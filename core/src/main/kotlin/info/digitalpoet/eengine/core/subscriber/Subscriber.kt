package info.digitalpoet.eengine.core.subscriber

import info.digitalpoet.eengine.core.message.ChannelMatcher

/** <!-- Documentation for: info.digitalpoet.eengine.core.subscriber.Subscriber on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class Subscriber(val id: String, val serviceId: String, val channelMatcher: ChannelMatcher, val deliverer: Deliverer)
