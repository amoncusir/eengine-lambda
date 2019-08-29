package info.digitalpoet.eengine.core.postwoman

import info.digitalpoet.eengine.core.subscriber.Service
import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.postwoman.ExclusiveBroadcastHandler on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class ExclusiveBroadcastHandler: BroadcastHandler
{
    override val type: String = "exclusive"

    override fun select(services: List<Service>): List<Subscriber>
    {
        val serviceIndex = getIndex(services.size)
        val service: Service = services[serviceIndex]
        val subsIndex = getIndex(service.subscribers.size)
        return listOf(service.subscribers[subsIndex])
    }

    private fun getIndex(max: Int): Int
    {
        return (Math.random() * max).toInt()
    }
}
