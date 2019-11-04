package info.digitalpoet.eengine.core.service

import info.digitalpoet.eengine.core.message.Message

/** <!-- Documentation for: info.digitalpoet.eengine.core.manager.MessageService on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface MessageManager
{
    fun delivery(message: Message)

    fun deliveryTo(subscriberId: String, message: Message)
}
