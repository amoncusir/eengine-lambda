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
import kotlinx.coroutines.io.writer
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import java.text.DateFormat
import java.text.SimpleDateFormat

/** <!-- Documentation for: info.digitalpoet.eengine.http.Server on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */

fun Application.installation()
{
    install(ContentNegotiation) {
        gson {
            setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        }
    }
}
