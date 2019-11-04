package info.digitalpoet.eengine.core.matcher

/** <!-- Documentation for: info.digitalpoet.eengine.core.matcher.ChannelMatcherFactoryDealer on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class MessageMatcherFactoryDealer(factories: List<MessageMatcherFactory>, private val default: MessageMatcherFactory)
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    private val factories: Map<String, MessageMatcherFactory> = factories.map { it.type to it }.toMap()

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    fun instance(type: String, data: Map<String, Any?>): MessageMatcher
    {
        return factories.getOrDefault(type, default).instance(data)
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
