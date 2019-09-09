package info.digitalpoet.eengine.core.broadcast

import info.digitalpoet.eengine.core.mockService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/** <!-- Documentation for: info.digitalpoet.eengine.core.broadcast.AllBroadcastHandler on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class AllBroadcastHandlerTest
{
    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    //~ Tests ==========================================================================================================

    @Test
    fun `Test AllBroadcastHandler type`()
    {
        val broadcast = AllBroadcastHandler()

        assertEquals("all", broadcast.type)
    }

    @Test
    fun `Test AllBroadcastHandler implementation`()
    {
        val services = listOf(mockService("service-one"), mockService("service-two"), mockService("service-three"))
        val broadcast = AllBroadcastHandler()
        val subscribers = broadcast.select(services)

        assertEquals(6, subscribers.size)
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
