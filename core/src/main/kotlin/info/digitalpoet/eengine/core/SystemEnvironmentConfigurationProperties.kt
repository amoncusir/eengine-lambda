package info.digitalpoet.eengine.core

/** <!-- Documentation for: info.digitalpoet.eengine.core.SystemEnvironmentConfigurationProperties on 23/11/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
class SystemEnvironmentConfigurationProperties: ConfigurationProperties
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    val env: Map<String, String> = System.getenv()

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override fun get(key: String): String? = env.get(key)

    override fun contains(key: String): Boolean = key in env

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
