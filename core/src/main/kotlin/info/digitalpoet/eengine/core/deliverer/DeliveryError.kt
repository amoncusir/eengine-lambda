package info.digitalpoet.eengine.core.deliverer

import info.digitalpoet.eengine.core.EEngineError

/** <!-- Documentation for: info.digitalpoet.eengine.core.deliverer.DeliveryError on 8/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class DeliveryError(override val message: String, override val cause: Throwable? = null):
    EEngineError(message, cause)
