package info.digitalpoet.eengine.core.deliverer

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import info.digitalpoet.eengine.core.EEngineError
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/** <!-- Documentation for: info.digitalpoet.eengine.core.deliverer.DelivererFactoryDealTest on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class DelivererFactoryDealTest
{
    var mockedFactories: List<DelivererFactory>? = null

    //~ BeforeAll ======================================================================================================

    //~ BeforeEach =====================================================================================================

    @BeforeEach
    fun prepare()
    {
        mockedFactories = listOf(
            mock {
                on { type } doReturn "one"
                on { instance(any()) } doReturn mock()
            },
            mock {
                on {
                    type
                } doReturn "two"
            },
            mock {
                on {
                    type
                } doReturn "three"
            }
        )
    }

    //~ Tests ==========================================================================================================

    @Test
    fun `Instance of class and get a instance from existing key`()
    {
        val dealer = DelivererFactoryDealer(mockedFactories!!)
        val instance = dealer.instance("one", mapOf<String, Any?>())

        assertThat(instance, not(nullValue()))
    }

    @Test
    fun `Instance of class and get a instance from unknown key`()
    {
        val dealer = DelivererFactoryDealer(mockedFactories!!)

        assertThrows<EEngineError> { dealer.instance("unknown", mapOf<String, Any?>()) }
    }

    //~ AfterEach ======================================================================================================

    //~ AfterAll =======================================================================================================

}
