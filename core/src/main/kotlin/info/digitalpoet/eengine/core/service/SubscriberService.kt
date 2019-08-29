package info.digitalpoet.eengine.core.service

import javax.json.JsonObject

/** <!-- Documentation for: info.digitalpoet.eengine.core.service.SubscriberService on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
data class SubscriberPetition(
    val id: String,
    val serviceId: String,
    val channelDescriptor: String,
    val delivererType: String,
    val delivererConfig: JsonObject
)

/** <!-- Documentation for: info.digitalpoet.eengine.core.service.SubscriberService on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface SubscriberService
{
    fun registerSubscriber(petition: SubscriberPetition)

    fun removeSubscriber(id: String)
}
