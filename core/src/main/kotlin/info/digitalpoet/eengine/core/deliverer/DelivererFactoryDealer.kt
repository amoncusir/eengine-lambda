package info.digitalpoet.eengine.core.deliverer

/** <!-- Documentation for: info.digitalpoet.eengine.core.deliverer.DelivererFactoryDealer on 29/8/19 -->
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

    fun instance(type: String, config: Map<String, Any?>): Deliverer
    {
        return factories[type]?.instance(config) ?: throw NotFoundAnyDelivererError(
            type
        )
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
