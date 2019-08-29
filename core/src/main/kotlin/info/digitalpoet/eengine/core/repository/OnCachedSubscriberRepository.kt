package info.digitalpoet.eengine.core.repository

import info.digitalpoet.eengine.core.subscriber.Service
import info.digitalpoet.eengine.core.subscriber.Subscriber
import java.util.Date

/** <!-- Documentation for: info.digitalpoet.eengine.core.repository.OnMemorySubscriberRepository on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
open class OnCachedSubscriberRepository(
    protected val persistentRepository: SubscriberRepository,
    val ttl: Int = 10000, // 10 seconds
    protected val hardConsistency: Boolean = false
    ):
    SubscriberRepository
{
    private val subscribers: MutableList<Subscriber> = mutableListOf()

    private var lastCheck: Long = Date().time

    override fun save(subscriber: Subscriber)
    {
        persistentRepository.save(subscriber)
    }

    override fun findByChannel(channel: String): List<Service>
    {
        if (needRefresh()) updateSubscribers()

        return subscribers
            .filter { it.channelMatcher.match(channel) }
            .groupBy { it.serviceId }
            .map { Service(it.key, it.value) }
    }

    override fun getAllSubscribers(): List<Subscriber>
    {
        return if (needRefresh())
            updateSubscribers()
        else
            subscribers
    }

    override fun deleteSubscriber(id: String)
    {
        persistentRepository.deleteSubscriber(id)

        if (hardConsistency)
        {
            subscribers.removeIf { it.id == id }
        }
    }

    protected fun needRefresh(): Boolean = lastCheck < Date().time - ttl

    protected fun updateSubscribers(): MutableList<Subscriber>
    {
        subscribers.clear()
        subscribers.addAll(persistentRepository.getAllSubscribers())

        return subscribers
    }
}
