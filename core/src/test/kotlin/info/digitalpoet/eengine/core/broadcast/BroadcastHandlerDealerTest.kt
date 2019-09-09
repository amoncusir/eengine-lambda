package info.digitalpoet.eengine.core.broadcast

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import info.digitalpoet.eengine.core.mockSubscriber
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** <!-- Documentation for: info.digitalpoet.eengine.core.broadcast.BroadcastHandlerDealerTest on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class BroadcastHandlerDealerTest
{
    lateinit var broadcasts: List<BroadcastHandler>

    lateinit var defaultBroadcast: BroadcastHandler

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    @BeforeEach
    fun prepare()
    {
        val subscribers = listOf(mockSubscriber("sub-one"), mockSubscriber("sub-two"), mockSubscriber("sub-three"))

        defaultBroadcast = mock {
            on { type } doReturn "default"
            on { select(any()) } doReturn subscribers
        }

        broadcasts = listOf(
            mock {
                on { type } doReturn "one"
                on { select(any()) } doReturn subscribers
            },
            mock {
                on { type } doReturn "two"
                on { select(any()) } doReturn subscribers
            },
            mock {
                on { type } doReturn "three"
                on { select(any()) } doReturn subscribers
            }
        )
    }

    //~ Tests ==========================================================================================================

    @Test
    fun `Test instance method`()
    {
        val dealer = BroadcastHandlerDealer(broadcasts, defaultBroadcast)

        val broadcast = dealer.instance("one")

        assertEquals("one", broadcast.type)
    }

    @Test
    fun `Test instance method with default`()
    {
        val dealer = BroadcastHandlerDealer(broadcasts, defaultBroadcast)

        val broadcast = dealer.instance("unknown")

        assertEquals("default", broadcast.type)
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
