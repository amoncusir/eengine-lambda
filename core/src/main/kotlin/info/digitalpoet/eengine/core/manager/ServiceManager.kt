package info.digitalpoet.eengine.core.manager

import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.subscriber.Service

/** <!-- Documentation for: info.digitalpoet.eengine.core.manager.ServiceManager on 4/11/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface ServiceManager
{
    fun findServices(message: Message): List<Service>
}
