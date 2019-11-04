package info.digitalpoet.eengine.core.postwoman

import info.digitalpoet.eengine.core.broadcast.BroadcastHandlerDealer
import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.MessageConfiguration
import info.digitalpoet.eengine.core.orchestrator.Orchestrator
import info.digitalpoet.eengine.core.repository.MessageRepository
import info.digitalpoet.eengine.core.repository.SubscriberRepository
import info.digitalpoet.eengine.core.service.MessageService
import info.digitalpoet.eengine.core.subscriber.Service
import mu.KotlinLogging

/** <!-- Documentation for: info.digitalpoet.eengine.core.postwoman.PostwomanMessageService on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class PostwomanMessageService(
    private val messageRepository: MessageRepository,
    private val subscriberRepository: SubscriberRepository,
    private val broadcastHandlerDealer: BroadcastHandlerDealer,
    private val defaultMessageConfiguration: MessageConfiguration,
    private val orchestrator: Orchestrator
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
        logger.debug { "New delivery: $message" }

        saveMessage(message)

        val configuration = MessageConfiguration.merge(message.configuration, defaultMessageConfiguration)
        val services = findServices(message.channel)
        val broadcastHandler = broadcastHandlerDealer.instance(configuration.broadcastType!!)
        val subscribers = broadcastHandler.select(services)

        if (subscribers.isNotEmpty())
        {
            for (subscriber in subscribers)
            {
                orchestrator.put(message, subscriber, configuration);
            }
        }
        else
        {
            throwErrorNotFoundSubscriber("Not found any subscriber by broadcast: ${broadcastHandler.type}", message)
        }
    }

    override fun deliveryTo(subscriberId: String, message: Message)
    {
        logger.debug { "New delivery directly to subscriber: $subscriberId -> $message" }

        saveMessage(message)

        val configuration = MessageConfiguration.merge(message.configuration, defaultMessageConfiguration)
        val subscriber = subscriberRepository.findById(subscriberId) ?:
            throwErrorNotFoundSubscriber("Not found any subscriber by id: $subscriberId", message)

        orchestrator.put(message, subscriber, configuration)
    }

    //~ Methods ========================================================================================================

    protected open fun saveMessage(message: Message)
    {
        messageRepository.save(message);
    }

    protected open fun findServices(channel: String): List<Service>
    {
        return subscriberRepository.findByChannel(channel)
    }

    protected fun throwErrorNotFoundSubscriber(text: String, message: Message): Nothing
    {
        logger.error { "NotFoundAnySubscriber: $text - with message: $message" }

        throw NotFoundAnySubscriber(text)
    }

    //~ Operators ======================================================================================================
}
