package com.summer.passwordcompose.repositories

interface UserRepository {
    //region Preference methods
    suspend fun save(fullName: String?, mobileNumber: String?)
    suspend fun getFullName(): String?
    suspend fun getMobileNumber(): String?
    suspend fun getPin(): String?
    suspend fun setPin(pin: String)
    fun isFingerPrintSet(): Boolean
    fun setFingerPrint(isFingerPrintSet: Boolean)
    //endregion
}