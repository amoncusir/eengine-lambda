package info.digitalpoet.eengine.core.service

import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.manager.SubscriberService on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
data class SubscriberPetition(
    val id: String,
    val serviceId: String,
    val channelType: String,
    val channelConfig: Map<String, Any?>,
    val delivererType: String,
    val delivererConfig: Map<String, Any?>
)

/** <!-- Documentation for: info.digitalpoet.eengine.core.manager.SubscriberService on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface SubscriberManager
{
    fun createSubscriber(petition: SubscriberPetition)

    fun removeSubscriber(id: String)

    fun getAllSubscribers(): List<Subscriber>
}
