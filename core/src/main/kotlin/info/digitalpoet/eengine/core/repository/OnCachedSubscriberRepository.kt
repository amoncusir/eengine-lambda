package info.digitalpoet.eengine.core.repository

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

    private var lastCheck: Long? = null

    override fun save(subscriber: Subscriber)
    {
        persistentRepository.save(subscriber)
    }

    override fun findById(subscriberId: String): Subscriber?
    {
        if (needRefresh()) updateSubscribers()

        return subscribers.find { it.id == subscriberId }
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

    protected open fun needRefresh(): Boolean = lastCheck?.let { lastCheck!! < Date().time - ttl } ?: true

    protected open fun updateSubscribers(): MutableList<Subscriber>
    {
        subscribers.clear()
        subscribers.addAll(persistentRepository.getAllSubscribers())

        lastCheck = Date().time

        return subscribers
    }
}
