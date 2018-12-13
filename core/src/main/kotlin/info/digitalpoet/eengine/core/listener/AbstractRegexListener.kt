package info.digitalpoet.eengine.core.listener

/** <!-- Documentation for: info.digitalpoet.eengine.core.listener.AbstractRegexListener on 23/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
abstract class AbstractRegexListener(private val matchChannel: Regex): Listener
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    constructor(channel: String): this(Regex(channel))

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    override fun isListening(channel: String): Boolean =  matchChannel.matches(channel)

    //~ Operators ======================================================================================================
}
