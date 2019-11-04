package info.digitalpoet.eengine.core.matcher

import info.digitalpoet.eengine.core.message.Message

/** <!-- Documentation for: info.digitalpoet.eengine.core.matcher.MessageMatcher on 4/11/19 -->
 *
 * @author Aran Moncusí Ramírez
 */
interface MessageMatcher
{
    fun match(message: Message): Boolean
}
