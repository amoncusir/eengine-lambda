package info.digitalpoet.eengine.core.subscriber

import javax.json.JsonObject

/** <!-- Documentation for: info.digitalpoet.eengine.core.subscriber.DelivererFactoryDealer on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class DelivererFactoryDealer(factories: List<DelivererFactory>): DelivererFactory
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    override val type: String = "none"

    private val factories: Map<String, DelivererFactory> = mutableMapOf(*factories.map { it.type to it }.toTypedArray())

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override fun instance(type: String, config: JsonObject): Deliverer?
    {
        return (factories[type] ?: throw NotFoundAnyDelivererError(type)).instance(type, config)
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
