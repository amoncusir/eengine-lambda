package info.digitalpoet.eengine.core.service

/** <!-- Documentation for: info.digitalpoet.eengine.core.service.SubscriberService on 29/8/19 -->
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

/** <!-- Documentation for: info.digitalpoet.eengine.core.service.SubscriberService on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface SubscriberService
{
    fun createSubscriber(petition: SubscriberPetition)

    fun removeSubscriber(id: String)
}
