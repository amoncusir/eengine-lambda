package info.digitalpoet.eengine.core.repository

import info.digitalpoet.eengine.core.message.Message

/** <!-- Documentation for: info.digitalpoet.eengine.core.repository.MessageRepository on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface MessageRepository
{
    fun save(message: Message)

    fun findById(id: String): Message?
}
