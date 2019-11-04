package info.digitalpoet.eengine.core

/** <!-- Documentation for: info.digitalpoet.eengine.core.EEngineError on 8/9/19 -->
 *
 * Main error class. All errors threw from core logic and their implementations must be extending from this class.
 *
 * @author Aran Moncusí Ramírez
 */
open class EEngineError(message: String?, cause: Throwable? = null) : RuntimeException(message, cause)
