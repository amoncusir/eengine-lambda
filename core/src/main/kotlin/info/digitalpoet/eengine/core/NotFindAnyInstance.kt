package info.digitalpoet.eengine.core

import java.lang.RuntimeException
import kotlin.reflect.KClass

/** <!-- Documentation for: info.digitalpoet.eengine.core.NotFindAnyInstance on 23/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class NotFindAnyInstance(klass: KClass<*>, action: String?, cause: Throwable? = null):
    RuntimeException("Not find any instance of ${klass.qualifiedName} in action: $action", cause)
