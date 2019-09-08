package info.digitalpoet.eengine.core.message

import javax.json.JsonObject

/** <!-- Documentation for: info.digitalpoet.eengine.core.message.ChannelMatcherFactory on 29/8/19 -->
 *
 * Factory implementation for class `ChannelMatcher`
 *
 * @author Aran Moncusí Ramírez
 */
interface ChannelMatcherFactory
{
    val type: String

    fun instance(data: JsonObject): ChannelMatcher
}
