package ltd.hlaeja.jwt.util

import java.security.KeyException
import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.Base64.getDecoder

object PublicKeyProvider {

    fun load(
        pemFile: String,
    ): RSAPublicKey = readPublicPemFile(pemFile)
        .let(PublicKeyProvider::makePublicKey)

    private fun makePublicKey(
        publicKeyBytes: ByteArray,
    ): RSAPublicKey = KeyFactory.getInstance("RSA")
        .generatePublic(X509EncodedKeySpec(publicKeyBytes)) as RSAPublicKey

    private fun readPublicPemFile(
        publicKey: String,
    ): ByteArray = javaClass.classLoader
        ?.getResource(publicKey)
        ?.readText()
        ?.let(PublicKeyProvider::getPublicKeyByteArray)
        ?: throw KeyException("Could not load public key")

    private fun getPublicKeyByteArray(
        keyText: String,
    ): ByteArray = keyText.replace(Regex("[\r\n]+"), "")
        .removePrefix("-----BEGIN PUBLIC KEY-----")
        .removeSuffix("-----END PUBLIC KEY-----")
        .let { getDecoder().decode(it) }
}
