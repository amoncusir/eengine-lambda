package info.digitalpoet.eengine.http

import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.service.MessageService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.routing
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun Application.initDelivery(service: MessageService)
{
    // Publish a message

    routing {
        post("/publish") {
            val message = call.receive<Message>()

            call.respond(HttpStatusCode.Created, Unit)

            GlobalScope.launch {
                service.delivery(message)
            }
        }
    }
}

/** <!-- Documentation for: info.digitalpoet.eengine.http.DeliveryModule on 10/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class DeliveryModule
{}
