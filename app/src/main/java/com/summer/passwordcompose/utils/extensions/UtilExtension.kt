package com.summer.passwordcompose.utils.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.summer.passwordcompose.data.entities.VaultEntity

inline fun <reified T> Gson.fromJson(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)

fun List<Int>?.getIntArray(): String? {
    if (this == null) return null
    return if (this.isEmpty()) {
        "[]"
    } else {
        var resultString = "["
        this.forEach {
            resultString += it.toString()
            resultString += ","
        }
        resultString = resultString.substring(0, resultString.length - 1)
        resultString += "]"
        resultString
    }
}

/**
 * To check contains based on entityName, webAddress, notes
 */
fun VaultEntity.contains(searchValue: String): Boolean {
    return entityName.lowercase().contains(searchValue.lowercase()) ||
            webAddress.lowercase().contains(searchValue.lowercase()) ||
            usernameMobileCardNumber.lowercase().contains(searchValue.lowercase()) ||
            notes.lowercase().contains(searchValue.lowercase())
}

fun VaultEntity.filterByTagSearchValue(tagId: String?, searchValue: String): Boolean {
    return if (tagId == null) {
        contains(searchValue)
    } else {
        contains(searchValue) && (tagEntity?.id ?: "") == tagId
    }
}