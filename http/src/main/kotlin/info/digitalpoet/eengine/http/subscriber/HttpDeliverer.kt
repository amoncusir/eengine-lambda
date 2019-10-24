package info.digitalpoet.eengine.http.subscriber

import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.deliverer.Deliverer
import info.digitalpoet.eengine.core.deliverer.DeliveryError
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess

/** <!-- Documentation for: info.digitalpoet.eengine.http.subscriber.HttpDeliverer on 10/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class HttpDeliverer(
    private val client: HttpClient,
    private val endpoint: String
): Deliverer
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override suspend fun delivery(message: Message)
    {
        val status = client.get<HttpStatusCode>(endpoint)

        if (!status.isSuccess())
            throw DeliveryError("Error to send message with code: ${status.value} | description: ${status.description}")
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
