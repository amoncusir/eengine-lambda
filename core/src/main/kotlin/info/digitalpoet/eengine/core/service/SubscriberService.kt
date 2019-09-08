package info.digitalpoet.eengine.core.service

import javax.json.JsonObject

/** <!-- Documentation for: info.digitalpoet.eengine.core.service.SubscriberService on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
data class SubscriberPetition(
    val id: String,
    val serviceId: String,
    val channelType: String,
    val channelConfig: JsonObject,
    val delivererType: String,
    val delivererConfig: JsonObject
)

/** <!-- Documentation for: info.digitalpoet.eengine.core.service.SubscriberService on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface SubscriberService
{
    fun createSubscriber(petition: SubscriberPetition)

    fun removeSubscriber(id: String)
}
