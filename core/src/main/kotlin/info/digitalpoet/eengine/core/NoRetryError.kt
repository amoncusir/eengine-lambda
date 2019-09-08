package info.digitalpoet.eengine.core

import java.lang.RuntimeException

/** <!-- Documentation for: info.digitalpoet.eengine.core.NoRetryError on 8/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class NoRetryError(message: String?, cause: Throwable?) : RuntimeException(message, cause)
