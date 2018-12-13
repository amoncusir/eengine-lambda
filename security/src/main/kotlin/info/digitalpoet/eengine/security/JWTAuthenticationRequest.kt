package info.digitalpoet.eengine.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.DecodedJWT
import info.digitalpoet.eengine.core.request.AuthenticatedRequest

/** <!-- Documentation for: info.digitalpoet.eengine.security.JWTAuthenticationRequest on 11/12/18 -->
 *
 * @author Aran Moncusí Ramírez
 */
abstract class JWTAuthenticationRequest(
    override val channel: String,
    val token: String
):
    AuthenticatedRequest
{
    //~ Constants ======================================================================================================

    companion object
    {
        fun decodeToken(token: String): DecodedJWT
        {
            try
            {
                return JWT.decode(token)
            }
            catch (e: JWTDecodeException)
            {
                throw RuntimeException("The token has a invalid format: $token", e)
            }
        }
    }

    //~ Values =========================================================================================================

    val decodedToken: DecodedJWT = decodeToken(token)

    val claims: Map<String, Claim> = decodedToken.claims!!

    override val clientId: String = decodedToken.issuer ?: throw IllegalArgumentException("Token must have issuer")

    //~ Properties =====================================================================================================

    //~ Constructors ===================================================================================================

    //~ Open Methods ===================================================================================================

    //~ Methods ========================================================================================================

    override fun matchKey(key: ByteArray): Boolean
    {
        val verifier = makeVerifier(key)

        try
        {
            verifier.verify(token)
            return true
        }
        catch (e: JWTVerificationException)
        {
            return false
        }
    }

    protected open fun getAlgorithm(key: ByteArray): Algorithm = Algorithm.HMAC256(key)

    protected open fun makeVerifier(key: ByteArray): JWTVerifier
    {
        return JWT.require(getAlgorithm(key))
            .withIssuer(clientId)
            .build()
    }

    //~ Operators ======================================================================================================
}
