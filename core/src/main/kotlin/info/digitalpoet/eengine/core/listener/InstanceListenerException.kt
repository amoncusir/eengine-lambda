package info.digitalpoet.eengine.core.listener

import info.digitalpoet.eengine.core.event.Event

/** <!-- Documentation for: info.digitalpoet.eengine.core.listener.BroadcastListenerException on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class InstanceListenerException(val listenerId: String, message: String, cause: Throwable? = null):
    ListenerException("[$listenerId]: $message", cause)
