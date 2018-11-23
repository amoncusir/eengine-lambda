package info.digitalpoet.eengine.core.event

import java.lang.RuntimeException

/** <!-- Documentation for: info.digitalpoet.eengine.core.event.EventBroadcastException on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
class EventBroadcastException(val event: Event, message: String, cause: Throwable? = null):
    RuntimeException(message, cause)
