package info.digitalpoet.eengine.core.subscriber

import javax.json.JsonObject

/** <!-- Documentation for: info.digitalpoet.eengine.core.subscriber.DelivererFactory on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface DelivererFactory
{
    val type: String

    fun instance(type: String, config: JsonObject): Deliverer?
}
