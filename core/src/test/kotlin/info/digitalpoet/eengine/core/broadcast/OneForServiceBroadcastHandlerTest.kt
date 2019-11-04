package info.digitalpoet.eengine.core.broadcast

import info.digitalpoet.eengine.core.mockService
import info.digitalpoet.eengine.core.subscriber.Service
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** <!-- Documentation for: info.digitalpoet.eengine.core.broadcast.OneForServiceBroadcastHandlerTest on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class OneForServiceBroadcastHandlerTest
{
    lateinit var services: List<Service>

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    @BeforeEach
    fun prepare()
    {
        services = listOf(mockService("manager-one"), mockService("manager-two"), mockService("manager-three"))
    }

    //~ Tests ==========================================================================================================

    @Test
    fun `OneForServiceBroadcastHandler implementation`()
    {
        val broadcast = OneForServiceBroadcastHandler()

        val subscribers = broadcast.select(services)

        Assertions.assertEquals(3, subscribers.size)
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

    //~ Util Methods ===================================================================================================
}
