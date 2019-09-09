package info.digitalpoet.eengine.core.postwoman

import info.digitalpoet.eengine.core.broadcast.BroadcastHandlerDealer
import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.MessageConfiguration
import info.digitalpoet.eengine.core.repository.MessageRepository
import info.digitalpoet.eengine.core.repository.SubscriberRepository
import info.digitalpoet.eengine.core.service.MessageService
import info.digitalpoet.eengine.core.subscriber.DeliveryError
import info.digitalpoet.eengine.core.subscriber.Service
import info.digitalpoet.eengine.core.subscriber.Subscriber
import mu.KotlinLogging

/** <!-- Documentation for: info.digitalpoet.eengine.core.postwoman.PostwomanMessageService on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class PostwomanMessageService(
    private val messageRepository: MessageRepository,
    private val subscriberRepository: SubscriberRepository,
    private val broadcastHandlerDealer: BroadcastHandlerDealer,
    private val defaultMessageConfiguration: MessageConfiguration

):
    MessageService
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    private val logger = KotlinLogging.logger {}

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override fun delivery(message: Message)
    {
        saveMessage(message)

        val configuration = MessageConfiguration.merge(message.configuration, defaultMessageConfiguration)
        val services = findServices(message.channel)
        val broadcastHandler = broadcastHandlerDealer.instance(configuration.broadcastType!!)
        val subscribers = broadcastHandler.select(services)

        if (subscribers.isNotEmpty())
        {
            var existAnyError: DeliveryError? = null

            for (subscriber in subscribers)
            {
                existAnyError = deliveryMessageToSubscriber(subscriber, message, configuration)
            }

            if (existAnyError is DeliveryError) throw existAnyError
        }
        else
        {
            throw NoFoundAnySubscriber("In channel: ${message.channel} with broadcast: ${broadcastHandler.type}")
        }
    }

    protected open fun deliveryMessageToSubscriber(
        subscriber: Subscriber,
        message: Message,
        configuration: MessageConfiguration
    ): DeliveryError?
    {
        val maxIntents = configuration.replyIntents!!;
        var deliveryError: DeliveryError? = null
        var intents = 0

        do
        {
            try
            {
                subscriber.deliverer.delivery(message)
            }
            catch (error: DeliveryError)
            {
                logger.error(error) { "Error when try to send message: ${message.id}" }

                intents++

                deliveryError = error

                continue
            }

            break

        } while (intents < maxIntents)

        return deliveryError
    }

    protected open fun saveMessage(message: Message)
    {
        messageRepository.save(message);
    }

    protected open fun findServices(channel: String): List<Service>
    {
        return subscriberRepository.findByChannel(channel)
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
