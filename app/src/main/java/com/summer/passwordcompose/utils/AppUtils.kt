package com.summer.passwordcompose.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.github.shamil.Xid
import com.google.gson.Gson
import com.summer.passwordcompose.beans.FileBean
import com.summer.passwordcompose.utils.extensions.fromJson
import java.util.Random
import kotlin.streams.asSequence

object AppUtils {
    //region Values
    const val KEY_FULL_NAME = "full_name"
    const val KEY_MOBILE_NUMBER = "mobile_number"
    const val KEY_PIN = "pin"
    const val KEY_FINGERPRINT_ENABLED = "fingerprint_enabled"
    const val KEY_ADD = "add"
    const val KEY_ALL = "all"
    //endregion

    //region Functions
    fun generateXid() = Xid.get().toHexString()!!

    fun getCurrentTimeSecs() =
        System.currentTimeMillis() / 1000

    /**
     * Generate Random String
     * returns String of specified length
     */
    fun getRandomString(
        length: Int,
        hasUpperAlphas: Boolean = true,
        hasLowerAlphas: Boolean = true,
        hasNumbers: Boolean = true,
        hasSpecialCharacters: Boolean = true
    ): String {
        val upperAlphas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val lowerAlphas = "abcdefghijklmnopqrstuvwxyz"
        val numbers = "0123456789"
        val specialChars = "@#$%^&*"
        if (length < 4) {
            throw RuntimeException("Length < 4")
        }
        val stringBuilder = StringBuilder()
        if (hasUpperAlphas) {
            stringBuilder.append(upperAlphas)
        }
        if (hasLowerAlphas) {
            stringBuilder.append(lowerAlphas)
        }
        if (hasNumbers) {
            stringBuilder.append(numbers)
        }
        if (hasSpecialCharacters) {
            stringBuilder.append(specialChars)
        }
        return Random().ints(length.toLong(), 0, stringBuilder.toString().length)
            .asSequence()
            .map(stringBuilder.toString()::get)
            .joinToString("")
    }
    //endregion

    fun copyText(context: Context?, textToBeCopied: String) {
        (context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?)?.setPrimaryClip(
            ClipData.newPlainText(
                "Copy", textToBeCopied
            )
        )
    }

    fun FileBean.toJSON(): String = Gson().toJson(this)

    fun String.toFileBean() = Gson().fromJson<FileBean>(this)!!

    fun getAppName(context: Context): String {
        val applicationInfo = context.applicationInfo
        val stringId = applicationInfo.labelRes
        return if (stringId == 0) {
            applicationInfo.nonLocalizedLabel.toString()
        } else {
            context.getString(stringId)
        }
    }
}