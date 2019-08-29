package info.digitalpoet.eengine.core.subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.NotFoundAnyChannelMatcher on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class NotFoundAnyDelivererError(type: String): Throwable(
    "Not found any Deliverer for type: $type"
)
