package info.digitalpoet.eengine.core.message

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.MessageConfiguration on 29/8/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
data class MessageConfiguration(val broadcastType: String?, val metadata: Map<String, Any?>? = null)
{
    companion object
    {
        fun merge(origin: MessageConfiguration?, extends: MessageConfiguration): MessageConfiguration
        {
            return origin?.let {
                it.copy(
                    broadcastType = it.broadcastType ?: extends.broadcastType,
                    metadata = it.metadata ?: extends.metadata
                )
            } ?: extends
        }
    }
}
