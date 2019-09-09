package info.digitalpoet.eengine.core

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import info.digitalpoet.eengine.core.message.ChannelMatcher
import info.digitalpoet.eengine.core.subscriber.Deliverer
import info.digitalpoet.eengine.core.subscriber.Service
import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.utils on 9/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
fun mockService(id: String): Service
{
    return Service(id, listOf(mockSubscriber("$id-one"), mockSubscriber("$id-two")))
}

fun mockSubscriber(id: String, deliverer: Deliverer = mock {}): Subscriber
{
    return Subscriber(id, "service-$id", mockChanelMatcher(id), deliverer)
}

fun mockChanelMatcher(channel: String): ChannelMatcher
{
    return mock {
        on { match(eq(channel)) } doReturn true
    }
}
