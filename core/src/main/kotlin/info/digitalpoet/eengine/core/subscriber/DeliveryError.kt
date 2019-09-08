package info.digitalpoet.eengine.core.subscriber

import info.digitalpoet.eengine.core.message.Message

/** <!-- Documentation for: info.digitalpoet.eengine.core.subscriber.DeliveryError on 8/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class DeliveryError(override val message: String, override val cause: Throwable?): RuntimeException(message, cause)
