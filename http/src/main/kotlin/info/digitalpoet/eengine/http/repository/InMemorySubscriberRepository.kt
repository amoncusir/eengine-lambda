package info.digitalpoet.eengine.http.repository

import info.digitalpoet.eengine.core.repository.SubscriberRepository
import info.digitalpoet.eengine.core.subscriber.Service
import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.http.repository.InMemorySubscriberRepository on 10/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class InMemorySubscriberRepository(subscribers: List<Subscriber>): SubscriberRepository
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    private val persistence: MutableMap<String, Subscriber> =
        mutableMapOf(*subscribers.map { it.id to it }.toTypedArray())

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override fun save(subscriber: Subscriber)
    {
        persistence[subscriber.id] = subscriber
    }

    override fun findByChannel(channel: String): List<Service>
    {
        return persistence.values
            .filter { it.channelMatcher.match(channel) }
            .groupBy { it.serviceId }
            .map { Service(it.key, it.value) }
    }

    override fun findById(subscriberId: String): Subscriber?
    {
        return persistence[subscriberId]
    }

    override fun getAllSubscribers(): List<Subscriber>
    {
        return persistence.values.toList()
    }

    override fun deleteSubscriber(id: String)
    {
        persistence.remove(id)
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
