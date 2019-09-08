package info.digitalpoet.eengine.core.message

import javax.json.JsonObject

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.ChannelMatcherFactoryDealer on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class ChannelMatcherFactoryDealer(factories: List<ChannelMatcherFactory>, private val default: ChannelMatcherFactory)
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    private val factories: Map<String, ChannelMatcherFactory> = factories.map { it.type to it }.toMap()

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    fun instance(type: String, data: JsonObject): ChannelMatcher
    {
        return factories.getOrDefault(type, default).instance(data)
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
