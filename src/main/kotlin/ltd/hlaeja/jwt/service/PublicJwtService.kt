package ltd.hlaeja.jwt.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import ltd.hlaeja.jwt.util.PublicKeyProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(prefix = "jwt", name = ["public-key"])
class PublicJwtService(
    @Value("\${jwt.public-key}")
    jwtPublicKey: String,
) {

    private val parser: JwtParser = Jwts.parser()
        .verifyWith(PublicKeyProvider.load(jwtPublicKey))
        .build()

    fun <T> verify(
        token: String,
        block: (claims: Jws<Claims>) -> T,
    ): T = parser.parseSignedClaims(token)
        .let(block)
}
