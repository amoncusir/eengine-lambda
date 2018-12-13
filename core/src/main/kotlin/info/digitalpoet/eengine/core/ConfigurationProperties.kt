package info.digitalpoet.eengine.core

/** <!-- Documentation for: info.digitalpoet.eengine.core.ConfigurationProperties on 22/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface ConfigurationProperties
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Methods ========================================================================================================

    operator fun get(key: String): String?

    operator fun contains(key: String): Boolean

    //~ Operators ======================================================================================================
}
