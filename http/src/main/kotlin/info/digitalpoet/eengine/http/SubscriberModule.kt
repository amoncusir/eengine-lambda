package info.digitalpoet.eengine.http

import info.digitalpoet.eengine.core.service.SubscriberService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing

fun Application.initSubscriber(service: SubscriberService)
{
    // Add a Subscriber
    // Remove a Subscriber
    // Check if it be a subscriber

    routing {
        post("/subscribe") {

        }

        delete("/subscribe") {

        }

        get("/subscribe") {
            call.respond(service.getAllSubscribers())
        }
    }
}

/** <!-- Documentation for: info.digitalpoet.eengine.http.SubscriberModule on 10/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class SubscriberModule
{}
