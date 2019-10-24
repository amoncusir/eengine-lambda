package info.digitalpoet.eengine.core.broadcast

import info.digitalpoet.eengine.core.subscriber.Service
import info.digitalpoet.eengine.core.subscriber.Subscriber
import kotlin.random.Random

/** <!-- Documentation for: info.digitalpoet.eengine.core.broadcast.ExclusiveBroadcastHandler on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class ExclusiveBroadcastHandler: BroadcastHandler
{
    override val type: String = "exclusive"

    override fun select(services: List<Service>): List<Subscriber>
    {
        val rand = Random(System.identityHashCode(services))
        return listOf(services.random(rand).subscribers.random(rand))
    }
}
