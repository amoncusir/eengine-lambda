package info.digitalpoet.eengine.core.matcher

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import info.digitalpoet.eengine.core.mockChanelMatcher
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** <!-- Documentation for: info.digitalpoet.eengine.core.matcher.ChannelMatcherFactoryDealerTest on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class ChannelMatcherFactoryDealerTest
{
    lateinit var factories: List<MessageMatcherFactory>

    lateinit var defaultFactory: MessageMatcherFactory

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    @BeforeEach
    fun prepare()
    {
        val matcher = mockChanelMatcher("channel")

        defaultFactory = mock {
            on { type } doReturn "default"
            on { instance(any()) } doReturn matcher
        }

        factories = listOf(
            mock {
                on { type } doReturn "one"
                on { instance(any()) } doReturn matcher
            },
            mock {
                on { type } doReturn "two"
                on { instance(any()) } doReturn matcher
            },
            mock {
                on { type } doReturn "three"
                on { instance(any()) } doReturn matcher
            }
        )
    }

    //~ Tests ==========================================================================================================

    @Test
    fun `ChannelMatcherFactoryDealer instance method with known type`()
    {
        val dealer = MessageMatcherFactoryDealer(factories, defaultFactory)
        val instance = dealer.instance("two", mock {})

        verify(factories[1], times(1)).instance(any())

        assert(instance.match(mock { on { channel } doReturn "channel" }))
    }

    @Test
    fun `ChannelMatcherFactoryDealer instance method without known type`()
    {
        val dealer = MessageMatcherFactoryDealer(factories, defaultFactory)
        val instance = dealer.instance("unknown", mock {})

        verify(defaultFactory, times(1)).instance(any())

        assert(instance.match(mock { on { channel } doReturn "channel" }))
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
