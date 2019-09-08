package info.digitalpoet.eengine.core.subscriber

import info.digitalpoet.eengine.core.NoRetryError

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.NotFoundAnyChannelMatcher on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class NotFoundAnyDelivererError(type: String): NoRetryError(
    "Not found any Deliverer for type: $type", null
)
