package info.digitalpoet.eengine.core.subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.subscriber.Service on 29/8/19 -->
 *
 * The services will be created when published message find your subscribers.
 * Any manager represents a collection of subscribers.
 *
 * Each [Service] represents an work group, like all instances of the same service.
 *
 * This association as used to select the [Subscriber] or many [Subscriber] to send the current
 * [info.digitalpoet.eengine.core.message.Message]. This decision will be taken by the
 * [info.digitalpoet.eengine.core.broadcast.BroadcastHandler] class implementation.
 *
 * @author Aran Moncusí Ramírez
 */
class Service(val id: String, val subscribers: List<Subscriber>)
