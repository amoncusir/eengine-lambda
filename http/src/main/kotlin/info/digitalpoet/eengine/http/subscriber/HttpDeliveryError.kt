package info.digitalpoet.eengine.http.subscriber

import info.digitalpoet.eengine.core.deliverer.DeliveryError

/** <!-- Documentation for: info.digitalpoet.eengine.http.subscriber.HttpDeliveryError on 10/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class HttpDeliveryError(message: String, cause: Throwable?) : DeliveryError(message, cause)
