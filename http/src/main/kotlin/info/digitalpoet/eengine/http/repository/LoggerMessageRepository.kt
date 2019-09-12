package info.digitalpoet.eengine.http.repository

import info.digitalpoet.eengine.core.message.ChannelMatcher
import info.digitalpoet.eengine.core.message.Message
import info.digitalpoet.eengine.core.message.StringChannelMatcher
import info.digitalpoet.eengine.core.repository.MessageRepository
import mu.KotlinLogging

/** <!-- Documentation for: info.digitalpoet.eengine.http.repository.LoggerMessageRepository on 10/9/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
class LoggerMessageRepository(private val errorMatcher: ChannelMatcher = StringChannelMatcher("error", true)):
    MessageRepository
{
    //~ Constants ======================================================================================================

    //~ Values =========================================================================================================

    private val logger = KotlinLogging.logger {}

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    override fun save(message: Message)
    {
        if (errorMatcher.match(message.channel))
            logger.error { "Save error message: $message" }
        else
            logger.debug { "Save message: $message" }
    }

    override fun findById(id: String): Message?
    {
        logger.debug { "findById: $id" }

        return null
    }

    //~ Methods ========================================================================================================

    //~ Operators ======================================================================================================
}
