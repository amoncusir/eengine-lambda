package info.digitalpoet.eengine.core.postwoman

import info.digitalpoet.eengine.core.message.ChannelMatcher
import info.digitalpoet.eengine.core.message.ChannelMatcherFactoryDealer
import info.digitalpoet.eengine.core.repository.SubscriberRepository
import info.digitalpoet.eengine.core.service.SubscriberPetition
import info.digitalpoet.eengine.core.service.SubscriberService
import info.digitalpoet.eengine.core.deliverer.Deliverer
import info.digitalpoet.eengine.core.deliverer.DelivererFactoryDealer
import info.digitalpoet.eengine.core.subscriber.Subscriber
import mu.KotlinLogging

/** <!-- Documentation for: info.digitalpoet.eengine.core.postwoman.PostwomanSubscriberService on 10/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class PostwomanSubscriberService(
    private val subscriberRepository: SubscriberRepository,
    private val matcherFactory: ChannelMatcherFactoryDealer,
    private val delivererFactory: DelivererFactoryDealer
):
    SubscriberService
{
    //~ Constants ======================================================================================================

    private val logger = KotlinLogging.logger {}

    //~ Values =========================================================================================================

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override fun createSubscriber(petition: SubscriberPetition)
    {
        val subscriber = Subscriber(petition.id, petition.serviceId, createMatcher(petition), createDeliverer(petition))

        logger.debug { "Create subscriber: $subscriber" }

        subscriberRepository.save(subscriber)
    }

    override fun getAllSubscribers(): List<Subscriber>
    {
        return subscriberRepository.getAllSubscribers()
    }

    override fun removeSubscriber(id: String)
    {
        logger.debug { "Petition for remove subscriber with id: $id" }

        subscriberRepository.deleteSubscriber(id)
    }

    //~ Methods ========================================================================================================

    protected open fun createMatcher(petition: SubscriberPetition): ChannelMatcher
    {
        return matcherFactory.instance(petition.channelType, petition.channelConfig)
    }

    protected open fun createDeliverer(petition: SubscriberPetition): Deliverer
    {
        return delivererFactory.instance(petition.delivererType, petition.delivererConfig)
    }

    //~ Operators ======================================================================================================
}
