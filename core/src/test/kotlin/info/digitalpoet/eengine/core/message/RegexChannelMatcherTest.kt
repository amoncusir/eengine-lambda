package info.digitalpoet.eengine.core.message

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.UUID

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.RegexChannelMatcherTest on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class RegexChannelMatcherTest
{
    val type = "regex"

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    //~ Tests ==========================================================================================================

    @Test
    fun `Factory test instance and type and regex expression with options`()
    {
        val factory = RegexChannelMatcher.Factory()
        val options: Map<String, Any?> = mapOf(
            "regex" to "[0-9a-f]{8}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{12}", // UUID without case
            "options" to arrayListOf("IGNORE_CASE")
        )

        val matcher = factory.instance(options)

        assertEquals(type, factory.type)
        assertThat(matcher.match(UUID.randomUUID().toString().toUpperCase()), equalTo(true))
        assertThat(matcher.match(UUID.randomUUID().toString().toLowerCase()), equalTo(true))
        assertThat(matcher.match("Mon Dieu"), equalTo(false))
    }

    @Test
    fun `Factory test instance and type and regex expression without options`()
    {
        val factory = RegexChannelMatcher.Factory()
        val options: Map<String, Any?> = mapOf(
            "regex" to "[0-9a-f]{8}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{12}" // UUID without case
        )

        val matcher = factory.instance(options)

        assertEquals(type, factory.type)
        assertThat(matcher.match(UUID.randomUUID().toString().toLowerCase()), equalTo(true))
        assertThat(matcher.match(UUID.randomUUID().toString().toUpperCase()), equalTo(false))
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
