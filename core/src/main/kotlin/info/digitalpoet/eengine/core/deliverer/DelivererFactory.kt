package info.digitalpoet.eengine.core.deliverer

/** <!-- Documentation for: info.digitalpoet.eengine.core.deliverer.DelivererFactory on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface DelivererFactory
{
    val type: String

    fun instance(config: Map<String, Any?>): Deliverer
}
