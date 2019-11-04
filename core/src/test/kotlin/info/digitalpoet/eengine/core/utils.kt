package info.digitalpoet.eengine.core

import com.nhaarman.mockitokotlin2.argWhere
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import info.digitalpoet.eengine.core.deliverer.Deliverer
import info.digitalpoet.eengine.core.matcher.MessageMatcher
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
    return Subscriber(id, "manager-$id", mockChanelMatcher(id), deliverer)
}

fun mockChanelMatcher(channel: String): MessageMatcher
{
    return mock {
        on { match(argWhere { it.channel == channel }) } doReturn true
    }
}
