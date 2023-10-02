package com.summer.passwordcompose.utils

import android.util.Base64
import com.summer.passwordcompose.utils.EncryptionUtils.toByteArray
import com.summer.passwordcompose.utils.EncryptionUtils.toCharArray
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESEncryption(
    key: CharArray,
    iv: CharArray,
) {

    private val algorithm = "AES/CBC/PKCS5Padding"
    private var keySpec: SecretKeySpec = SecretKeySpec(
        key.toByteArray(),
        "AES"
    )
    private var ivSpec: IvParameterSpec = IvParameterSpec(iv.toByteArray())

    fun decrypt(cipherText: String?): CharArray {
        return if (!cipherText.isNullOrEmpty()) {
            val cipher = Cipher.getInstance(algorithm).apply {
                init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
            }
            cipher.doFinal(Base64.decode(cipherText.toByteArray(), Base64.DEFAULT)).toCharArray()
        } else CharArray(0)
    }

    fun decryptToString(cipherText: String?): String {
        return if (!cipherText.isNullOrEmpty()) {
            val cipher = Cipher.getInstance(algorithm).apply {
                init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
            }
            String(cipher.doFinal(Base64.decode(cipherText.toByteArray(), Base64.DEFAULT)))
        } else ""
    }

    fun encrypt(inputText: String): String {
        val cipher = Cipher.getInstance(algorithm).apply {
            init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        }
        return Base64.encodeToString(cipher.doFinal(inputText.toByteArray()), Base64.DEFAULT)
    }

    fun encrypt(inputText: CharArray): String {
        val cipher = Cipher.getInstance(algorithm).apply {
            init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        }
        return Base64.encodeToString(cipher.doFinal(inputText.toByteArray()), Base64.DEFAULT)
    }

    companion object {
        fun keyGen(): Key {
            val keyGen = KeyGenerator.getInstance("AES")
            keyGen.init(256)
            return SecretKeySpec(keyGen.generateKey().encoded, "AES")
        }
    }
}