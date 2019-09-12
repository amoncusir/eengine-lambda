package info.digitalpoet.eengine.core.deliverer

import info.digitalpoet.eengine.core.message.Message

/** <!-- Documentation for: info.digitalpoet.eengine.core.deliverer.Deliverer on 29/8/19 -->
 *
 * Class to manage the delivery to the application service subscriber
 *
 * @author Aran Moncusí Ramírez
 */
interface Deliverer
{
    suspend fun delivery(message: Message)
}
