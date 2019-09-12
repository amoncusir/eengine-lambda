package info.digitalpoet.eengine.http.subscriber

import info.digitalpoet.eengine.core.deliverer.Deliverer
import info.digitalpoet.eengine.core.deliverer.DelivererFactory
import io.ktor.client.HttpClient

/** <!-- Documentation for: info.digitalpoet.eengine.http.subscriber.HttpDelivererFactory on 10/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class HttpDelivererFactory(private val client: HttpClient): DelivererFactory
{
    override val type: String = "http"

    override fun instance(config: Map<String, Any?>): Deliverer
    {
        return HttpDeliverer(client, config["endpoint"] as String)
    }
}
