package info.digitalpoet.eengine.core.matcher

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.UUID

/** <!-- Documentation for: info.digitalpoet.eengine.core.matcher.RegexChannelMatcherTest on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class RegexMessageMatcherTest
{
    val type = "regex"

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    //~ Tests ==========================================================================================================

    @Test
    fun `Factory test instance and type and regex expression with options`()
    {
        val factory = RegexMessageMatcher.Factory()
        val options: Map<String, Any?> = mapOf(
            "regex" to "[0-9a-f]{8}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{12}", // UUID without case
            "options" to arrayListOf("IGNORE_CASE")
        )

        val matcher = factory.instance(options)

        assertEquals(type, factory.type)
        assertThat(matcher.match(mock { on { channel } doReturn UUID.randomUUID().toString().toUpperCase() }),
                   equalTo(true))
        assertThat(matcher.match(mock { on { channel } doReturn UUID.randomUUID().toString().toLowerCase() }),
                   equalTo(true))

        assertThat(matcher.match(mock { on { channel } doReturn "Mon Dieu" }), equalTo(false))
    }

    @Test
    fun `Factory test instance and type and regex expression without options`()
    {
        val factory = RegexMessageMatcher.Factory()
        val options: Map<String, Any?> = mapOf(
            "regex" to "[0-9a-f]{8}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{4}\\-[0-9a-f]{12}" // UUID without case
        )

        val matcher = factory.instance(options)

        assertEquals(type, factory.type)

        assertThat(matcher.match(mock { on { channel } doReturn UUID.randomUUID().toString().toLowerCase() }),
                   equalTo(true))
        assertThat(matcher.match(mock { on { channel } doReturn UUID.randomUUID().toString().toUpperCase() }),
                   equalTo(false))
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
