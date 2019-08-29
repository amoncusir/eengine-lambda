package info.digitalpoet.eengine.core

import info.digitalpoet.eengine.core.message.ChannelMatcherFactory
import info.digitalpoet.eengine.core.repository.MessageRepository
import info.digitalpoet.eengine.core.repository.SubscriberRepository
import info.digitalpoet.eengine.core.subscriber.DelivererFactory

/** <!-- Documentation for: info.digitalpoet.eengine.core.ContextConfiguration on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface ContextConfiguration
{
    fun createSubscriberRepository(): SubscriberRepository

    fun createMessageRepository(): MessageRepository

    fun createChannelMatcherFactory(): ChannelMatcherFactory

    fun createDelivererFactory(): DelivererFactory
}
