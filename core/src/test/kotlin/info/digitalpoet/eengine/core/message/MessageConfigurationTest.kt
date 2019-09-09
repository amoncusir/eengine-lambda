package info.digitalpoet.eengine.core.message

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.MessageConfigurationTest on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class MessageConfigurationTest
{
    val defaultConfiguration = MessageConfiguration("type", 3, 3, "error", mapOf("one" to 1))

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    //~ Tests ==========================================================================================================

    @Test
    fun `Test merge static function with full configuration`()
    {
        val fullConfig = MessageConfiguration("full", 0, 0, "full/error", mapOf("full" to "full"))

        val merged = MessageConfiguration.merge(fullConfig, defaultConfiguration)

        assertEquals(fullConfig, merged)
    }

    @Test
    fun `Test merge static function with mid-fully configuration`()
    {
        val midConfig = MessageConfiguration("mid", 0, null, null, null)

        val merged = MessageConfiguration.merge(midConfig, defaultConfiguration)

        assertEquals(midConfig.broadcastType, merged.broadcastType)
        assertEquals(midConfig.replyIntents, merged.replyIntents)

        assertEquals(defaultConfiguration.errorIntents, merged.errorIntents)
        assertEquals(defaultConfiguration.errorChannel, merged.errorChannel)
        assertEquals(defaultConfiguration.metadata, merged.metadata)
    }

    @Test
    fun `Test merge static function with null configuration`()
    {
        val midConfig = null
        val merged = MessageConfiguration.merge(midConfig, defaultConfiguration)

        assertEquals(defaultConfiguration.broadcastType, merged.broadcastType)
        assertEquals(defaultConfiguration.replyIntents, merged.replyIntents)
        assertEquals(defaultConfiguration.errorIntents, merged.errorIntents)
        assertEquals(defaultConfiguration.errorChannel, merged.errorChannel)
        assertEquals(defaultConfiguration.metadata, merged.metadata)
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
