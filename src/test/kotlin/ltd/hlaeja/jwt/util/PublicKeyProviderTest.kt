package ltd.hlaeja.jwt.util

import java.security.KeyException
import java.security.interfaces.RSAPublicKey
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PublicKeyProviderTest {

    @Test
    fun `load public key - success`() {
        // given
        val pemFilePath = "cert/valid-public-key.pem"

        // when
        val publicKey: RSAPublicKey = PublicKeyProvider.load(pemFilePath)

        // then
        assertThat(publicKey).isNotNull
        assertThat(publicKey.algorithm).isEqualTo("RSA")
    }

    @Test
    fun `load public key - file does not exist`() {
        // given
        val nonExistentPemFilePath = "cert/non-existent.pem"

        // when exception
        val exception = org.junit.jupiter.api.assertThrows<KeyException> {
            PublicKeyProvider.load(nonExistentPemFilePath)
        }

        // then
        assertThat(exception.message).isEqualTo("Could not load public key")
    }

    @Test
    fun `load public key - file is invalid`() {
        // given
        val invalidPemFilePath = "cert/invalid-public-key.pem"

        // when exception
        val exception = org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            PrivateKeyProvider.load(invalidPemFilePath)
        }

        // then
        assertThat(exception.message).contains("Illegal base64 character 2d")
    }
}
