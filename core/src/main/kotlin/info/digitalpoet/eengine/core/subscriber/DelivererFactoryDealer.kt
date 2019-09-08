package info.digitalpoet.eengine.core.subscriber

import javax.json.JsonObject

/** <!-- Documentation for: info.digitalpoet.eengine.core.subscriber.DelivererFactoryDealer on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class DelivererFactoryDealer(factories: List<DelivererFactory>)
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    private val factories: Map<String, DelivererFactory> = mutableMapOf(*factories.map { it.type to it }.toTypedArray())

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    fun instance(type: String, config: JsonObject): Deliverer?
    {
        return (factories[type] ?: throw NotFoundAnyDelivererError(type)).instance(config)
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
