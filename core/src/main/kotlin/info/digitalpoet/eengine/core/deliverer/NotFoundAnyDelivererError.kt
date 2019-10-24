package info.digitalpoet.eengine.core.deliverer

import info.digitalpoet.eengine.core.EEngineError

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.NotFoundAnyChannelMatcher on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class NotFoundAnyDelivererError(type: String): EEngineError(
    "Not found any Deliverer for type: $type", null
)
