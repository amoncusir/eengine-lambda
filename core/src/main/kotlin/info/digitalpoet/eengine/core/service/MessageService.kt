package info.digitalpoet.eengine.core.service

import info.digitalpoet.eengine.core.message.Message

/** <!-- Documentation for: info.digitalpoet.eengine.core.service.MessageService on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface MessageService
{
    fun delivery(message: Message)
}
