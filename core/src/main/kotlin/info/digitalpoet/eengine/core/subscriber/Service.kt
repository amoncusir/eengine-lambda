package info.digitalpoet.eengine.core.subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.subscriber.Service on 29/8/19 -->
 *
 * The services will be created when published message find your subscribers
 *
 * @author Aran Moncusí Ramírez
 */
class Service(val id: String, val subscribers: List<Subscriber>)
