package info.digitalpoet.eengine.core.matcher

/** <!-- Documentation for: info.digitalpoet.eengine.core.matcher.ChannelMatcher on 29/8/19 -->
 *
 * encargado de procesar el canal y aceptar-lo
 *
 * @author Aran Moncusí Ramírez
 */
interface ChannelMatcher
{
    fun match(channel: String): Boolean
}
