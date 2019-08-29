package info.digitalpoet.eengine.core.postwoman

import info.digitalpoet.eengine.core.subscriber.Service
import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.postwoman.BroadcastHandler on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface BroadcastHandler
{
    val type: String

    fun select(services: List<Service>): List<Subscriber>
}
