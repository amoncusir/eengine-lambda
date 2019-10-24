package info.digitalpoet.eengine.core.broadcast

/** <!-- Documentation for: info.digitalpoet.eengine.core.broadcast.BroadcastHandlerDealer on 8/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class BroadcastHandlerDealer(instances: List<BroadcastHandler>, private val defaultInstance: BroadcastHandler)
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    private val instances: Map<String, BroadcastHandler> = instances.map { it.type to it }.toMap()

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    fun instance(type: String): BroadcastHandler
    {
        return instances.getOrDefault(type, defaultInstance)
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
