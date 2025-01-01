package ltd.hlaeja.jwt.service

import java.nio.charset.StandardCharsets.UTF_8
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import org.springframework.util.FileCopyUtils

class PublicJwtServiceTest {

    private lateinit var service: PublicJwtService

    @BeforeEach
    fun setup() {
        service = PublicJwtService("cert/valid-public-key.pem")
    }

    @Test
    fun `parse token with claims`() {
        // given
        val token = String(FileCopyUtils.copyToByteArray(ClassPathResource("jwt.token").inputStream), UTF_8).trim()

        // when
        val result = service.verify(token) { claims ->
            mapOf(
                "claim1" to claims.payload["claim1"] as String,
                "claim2" to claims.payload["claim2"] as Int,
            )
        }

        // then
        assertThat(result["claim1"]).isEqualTo("value1")
        assertThat(result["claim2"]).isEqualTo(123)
    }
}
