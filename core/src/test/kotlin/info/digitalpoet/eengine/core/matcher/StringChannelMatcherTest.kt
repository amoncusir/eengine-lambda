package info.digitalpoet.eengine.core.matcher

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** <!-- Documentation for: info.digitalpoet.eengine.core.matcher.StringChannelMatcherTest on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class StringChannelMatcherTest
{
    val type = "string"

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    //~ Tests ==========================================================================================================

    @Test
    fun `Factory test instance and type and match implementation without ignoreCase flag (to false)`()
    {
        val factory = StringChannelMatcher.Factory()
        val options = mapOf("keyword" to "match")

        val matcher = factory.instance(options)

        assertEquals(type, factory.type)
        assertThat(matcher.match("match"), equalTo(true))
        assertThat(matcher.match("MATCH"), equalTo(false))
        assertThat(matcher.match("Mon Dieu"), equalTo(false))
    }

    @Test
    fun `Factory test instance and type and match implementation with ignoreCase flag (to true)`()
    {
        val factory = StringChannelMatcher.Factory()
        val options = mapOf("keyword" to "match", "ignoreCase" to true)

        val matcher = factory.instance(options)

        assertEquals(type, factory.type)
        assertThat(matcher.match("match"), equalTo(true))
        assertThat(matcher.match("MATCH"), equalTo(true))
        assertThat(matcher.match("Mon Dieu"), equalTo(false))
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
