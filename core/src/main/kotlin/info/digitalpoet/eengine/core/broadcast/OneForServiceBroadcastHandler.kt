package info.digitalpoet.eengine.core.broadcast

import info.digitalpoet.eengine.core.subscriber.Service
import info.digitalpoet.eengine.core.subscriber.Subscriber
import kotlin.random.Random

/** <!-- Documentation for: info.digitalpoet.eengine.core.broadcast.OneForServiceBroadcastHandler on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class OneForServiceBroadcastHandler: BroadcastHandler
{
    override val type: String = "one-for-manager"

    override fun select(services: List<Service>): List<Subscriber>
    {
        val rand = Random(System.identityHashCode(services))
        return services.map { it.subscribers.random(rand) }
    }
}
