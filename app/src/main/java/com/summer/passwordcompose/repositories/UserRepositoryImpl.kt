package com.summer.passwordcompose.repositories

import android.content.SharedPreferences
import com.summer.passwordcompose.utils.AppUtils
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    UserRepository {
    override suspend fun save(fullName: String?, mobileNumber: String?) {
        sharedPreferences.edit()?.apply {
            putString(AppUtils.KEY_FULL_NAME, fullName).apply()
            putString(AppUtils.KEY_MOBILE_NUMBER, mobileNumber).apply()
        }
    }

    override suspend fun getFullName(): String? =
        sharedPreferences.getString(AppUtils.KEY_FULL_NAME, null)

    override suspend fun getMobileNumber(): String? =
        sharedPreferences.getString(AppUtils.KEY_MOBILE_NUMBER, null)

    override suspend fun getPin(): String? =
        sharedPreferences.getString(AppUtils.KEY_PIN, null)

    override suspend fun setPin(pin: String) {
        sharedPreferences.edit()?.apply {
            putString(AppUtils.KEY_PIN, pin).apply()
        }
    }

    override fun isFingerPrintSet(): Boolean =
        sharedPreferences.getBoolean(AppUtils.KEY_FINGERPRINT_ENABLED, false)

    override fun setFingerPrint(isFingerPrintSet: Boolean) {
        sharedPreferences.edit()?.apply {
            putBoolean(AppUtils.KEY_FINGERPRINT_ENABLED, isFingerPrintSet).apply()
        }
    }

}