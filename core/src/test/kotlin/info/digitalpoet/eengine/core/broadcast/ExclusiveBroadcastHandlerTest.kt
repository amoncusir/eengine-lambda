package info.digitalpoet.eengine.core.broadcast

import info.digitalpoet.eengine.core.mockService
import info.digitalpoet.eengine.core.subscriber.Service
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** <!-- Documentation for: info.digitalpoet.eengine.core.broadcast.ExclusiveBroadcastHandlerTest on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class ExclusiveBroadcastHandlerTest
{
    lateinit var services: List<Service>

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    @BeforeEach
    fun prepare()
    {
        services = listOf(mockService("service-one"), mockService("service-two"), mockService("service-three"))
    }

    //~ Tests ==========================================================================================================

    @Test
    fun `ExclusiveBroadcastHandler implementation`()
    {
        val broadcast = ExclusiveBroadcastHandler()

        val subscribers = broadcast.select(services)

        assertEquals(1, subscribers.size)
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
