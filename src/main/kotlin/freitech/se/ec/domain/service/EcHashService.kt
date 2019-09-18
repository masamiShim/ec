package freitech.se.ec.domain.service

import freitech.se.ec.config.SecurityHashConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import kotlin.experimental.and

@Service
class EcHashService {

    @Autowired
    private lateinit var securityHashConfig: SecurityHashConfig

    companion object {

        const val KeyLength = 256

        const val SaltAlgorithm = "SHA-256"
    }

    @Throws(IllegalStateException::class)
    fun hashed(value: String): String {
        val valueCharArray = value.toCharArray()
        val hashedSalt = getSalt()

        val keySpec: PBEKeySpec = PBEKeySpec(valueCharArray, hashedSalt, securityHashConfig.stretch, KeyLength)
        val secretKeyFactory = try {
            SecretKeyFactory.getInstance(securityHashConfig.algorithm)
        } catch (e: NoSuchAlgorithmException) {
            throw IllegalStateException("illegal algorithm set at value")
        }

        val secretKey = try {
            secretKeyFactory.generateSecret(keySpec)
        } catch (e: InvalidKeySpecException) {
            throw InvalidKeySpecException("cannot generate secret. check setting for hash")
        }

        val valByteArray = secretKey.encoded

        val sb = StringBuffer(64)
        for (b: Byte in valByteArray) {
            sb.append(String.format("%02x", b.and(0xFF.toByte())))
        }

        return sb.toString()
    }

    fun match(hashedValue: String, test: String): Boolean {
        return hashed(test) == hashedValue
    }

    private fun getSalt(): ByteArray {

        val messageDigest: MessageDigest = try {
            MessageDigest.getInstance(SaltAlgorithm)
        } catch (e: NoSuchAlgorithmException) {
            throw IllegalStateException("algorithm SHA-256 is  not found")
        }

        messageDigest.update(securityHashConfig.seed.toByteArray())
        return messageDigest.digest()
    }

}
