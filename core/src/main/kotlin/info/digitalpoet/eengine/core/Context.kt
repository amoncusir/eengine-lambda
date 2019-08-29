package info.digitalpoet.eengine.core

import info.digitalpoet.eengine.core.message.ChannelMatcherFactory
import info.digitalpoet.eengine.core.repository.MessageRepository
import info.digitalpoet.eengine.core.repository.SubscriberRepository
import info.digitalpoet.eengine.core.subscriber.DelivererFactory
import java.lang.NullPointerException

/** <!-- Documentation for: info.digitalpoet.eengine.core.Context on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class Context private constructor(
    val subscriberRepository: SubscriberRepository,
    val messageRepository: MessageRepository,
    val channelMatcherFactory: ChannelMatcherFactory,
    val delivererFactory: DelivererFactory
)
{
    companion object
    {
        private var pContext: Context? = null

        val context: Context
            get() = pContext ?: throw NullPointerException("Context not load")

        fun loadFromConfiguration(configuration: ContextConfiguration)
        {
            pContext = createUsingConfiguration(configuration)
        }

        private fun createUsingConfiguration(configuration: ContextConfiguration): Context = Context(
            configuration.createSubscriberRepository(),
            configuration.createMessageRepository(),
            configuration.createChannelMatcherFactory(),
            configuration.createDelivererFactory()
        )
    }
}
