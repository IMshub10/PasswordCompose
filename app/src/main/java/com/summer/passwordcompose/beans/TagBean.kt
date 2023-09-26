package com.summer.passwordcompose.beans

import com.google.gson.annotations.SerializedName

data class TagBean(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("created_at_app")
    var createdAtApp: Long = 0,
    @SerializedName("updated_at_app")
    var updatedAtApp: Long = 0,

    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String?,
)