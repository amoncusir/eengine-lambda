package info.digitalpoet.eengine.core.repository

import info.digitalpoet.eengine.core.subscriber.Service
import info.digitalpoet.eengine.core.subscriber.Subscriber

/** <!-- Documentation for: info.digitalpoet.eengine.core.repository.SubscriberRepository on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface SubscriberRepository
{
    fun save(subscriber: Subscriber)

    fun findByChannel(channel: String): List<Service>

    fun findById(subscriberId: String): Subscriber?

    fun getAllSubscribers(): List<Subscriber>

    fun deleteSubscriber(id: String)
}
