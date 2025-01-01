package ltd.hlaeja.jwt.service

import io.jsonwebtoken.Jwts
import java.security.interfaces.RSAPrivateKey
import ltd.hlaeja.jwt.util.PrivateKeyProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(prefix = "jwt", name = ["private-key"])
class PrivateJwtService(
    @Value("\${jwt.private-key}") jwtPrivateKey: String,
) {

    private var privateKey: RSAPrivateKey = PrivateKeyProvider.load(jwtPrivateKey)

    fun sign(
        vararg claim: Pair<String, Any>,
    ): String = Jwts.builder()
        .claims()
        .also { claims -> claim.forEach { claims.add(it.first, it.second) } }
        .and()
        .signWith(privateKey)
        .compact()
}
