package info.digitalpoet.eengine.core.subscriber

import info.digitalpoet.eengine.core.deliverer.Deliverer
import info.digitalpoet.eengine.core.matcher.MessageMatcher

/** <!-- Documentation for: info.digitalpoet.eengine.core.subscriber.Subscriber on 29/8/19 -->
 *
 * Subscriber represent any manager, function or something that can process events.
 *
 * *NOTE:* The method for send each event is responsibility of [Deliverer] instance.
 * *NOTE:* The method for know if the instance of [Subscriber] can accept the message is responsibility of [MessageMatcher]
 * instance.
 *
 * The Subscribers can be connect with others using the field [Subscriber.serviceId] that represents a [Service].
 * (_see [Service] docs for more information_].
 *
 * @author Aran Moncusí Ramírez
 */
class Subscriber(val id: String, val serviceId: String, val messageMatcher: MessageMatcher, val deliverer: Deliverer)
