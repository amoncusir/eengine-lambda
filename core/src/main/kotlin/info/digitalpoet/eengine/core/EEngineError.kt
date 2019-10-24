package info.digitalpoet.eengine.core

import java.lang.RuntimeException

/** <!-- Documentation for: info.digitalpoet.eengine.core.EEngineError on 8/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class EEngineError(message: String?, cause: Throwable? = null) : RuntimeException(message, cause)