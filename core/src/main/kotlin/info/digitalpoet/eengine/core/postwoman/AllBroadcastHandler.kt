package info.digitalpoet.eengine.core.postwoman

import info.digitalpoet.eengine.core.subscriber.Service
import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.postwoman.FullBroadcastHandler on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class AllBroadcastHandler: BroadcastHandler
{
    override val type: String = "all"

    override fun select(services: List<Service>): List<Subscriber>
    {
        return services
            .map { it.subscribers }
            .flatten()
    }
}
