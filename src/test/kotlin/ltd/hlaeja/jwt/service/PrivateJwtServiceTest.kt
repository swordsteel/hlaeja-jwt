package ltd.hlaeja.jwt.service

import java.util.Base64
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PrivateJwtServiceTest {

    private lateinit var service: PrivateJwtService

    @BeforeEach
    fun setup() {
        service = PrivateJwtService("cert/valid-private-key.pem")
    }

    @Test
    fun `make token with claims`() {
        // given
        val claim1 = "claim1" to "value1"
        val claim2 = "claim2" to 123

        // when
        val token = service.sign(claim1, claim2)

        // then
        assertThat(token).isNotEmpty()

        val parts = token.split("\\.".toRegex())
        assertThat(parts).hasSize(3)

        val header = String(Base64.getDecoder().decode(parts[0]))
        val payload = String(Base64.getDecoder().decode(parts[1]))
        assertThat(header).contains("RS256")
        assertThat(payload).contains("\"claim1\":\"value1\"", "\"claim2\":123")
    }

    @Test
    fun `make token with no claims`() {
        // when
        val token = service.sign()

        // then
        assertThat(token).isNotEmpty()

        val parts = token.split("\\.".toRegex())
        assertThat(parts).hasSize(3)

        val header = String(Base64.getDecoder().decode(parts[0]))
        val payload = String(Base64.getDecoder().decode(parts[1]))
        assertThat(header).contains("RS256")
        assertThat(payload).isEmpty()
    }
}
