package info.digitalpoet.eengine.core.postwoman

import info.digitalpoet.eengine.core.manager.ServiceManager
import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.repository.SubscriberRepository
import info.digitalpoet.eengine.core.subscriber.Service

/** <!-- Documentation for: info.digitalpoet.eengine.core.postwoman.RepositoryServiceManager on 4/11/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class RepositoryServiceManager(private val repository: SubscriberRepository): ServiceManager
{
    override fun findServices(message: Message): List<Service>
    {
        return repository.getAllSubscribers()
            .filter { it.messageMatcher.match(message) }
            .groupBy { it.serviceId }
            .map { Service(it.key, it.value) }
    }
}
