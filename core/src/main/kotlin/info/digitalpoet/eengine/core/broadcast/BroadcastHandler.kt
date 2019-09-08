package info.digitalpoet.eengine.core.broadcast

import info.digitalpoet.eengine.core.subscriber.Service
import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.broadcast.BroadcastHandler on 29/8/19 -->
 *
 * Encargado de enviar la publicacion a los subscriptores de los servicios
 *
 * @author Aran Moncusí Ramírez
 */
interface BroadcastHandler
{
    val type: String

    fun select(services: List<Service>): List<Subscriber>
}
