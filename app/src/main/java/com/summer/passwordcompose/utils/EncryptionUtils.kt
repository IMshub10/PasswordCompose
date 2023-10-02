package com.summer.passwordcompose.utils

import java.security.SecureRandom


object EncryptionUtils {

    fun generateRandomBytes(size: Int): ByteArray =
        ByteArray(size).apply {
            SecureRandom.getInstanceStrong().nextBytes(this)
        }

    fun CharArray.toByteArray(): ByteArray {
        val b = ByteArray(this.size)
        for (i in this.indices)
            b[i] = this[i].code.toByte()
        return b
    }

    fun ByteArray.toCharArray(): CharArray {
        val c = CharArray(this.size)
        for (i in this.indices)
            c[i] = this[i].toInt().toChar()
        return c
    }
}
