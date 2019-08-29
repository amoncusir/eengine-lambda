package info.digitalpoet.eengine.core.message

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.ChannelMatcherFactoryDealer on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class ChannelMatcherFactoryDealer(private val factories: List<ChannelMatcherFactory>): ChannelMatcherFactory
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    private val cache: MutableMap<String, ChannelMatcher> = mutableMapOf()

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override fun instance(channelDescriptor: String): ChannelMatcher
    {
        val cached: ChannelMatcher? = cache[channelDescriptor]

        if (cached != null) return cached

        for (factory in factories)
        {
            val matcher = factory.instance(channelDescriptor)

            if (matcher != null)
            {
                cache[channelDescriptor] = matcher
                return matcher
            }
        }

        throw NotFoundAnyChannelMatcherError(channelDescriptor)
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
