package com.summer.passwordcompose.beans

import com.google.gson.annotations.SerializedName

data class VaultBean(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("created_at_app")
    var createdAtApp: Long = 0,
    @SerializedName("updated_at_app")
    var updatedAtApp: Long = 0,

    @SerializedName("entity_name")
    var entityName: String,
    @SerializedName("web_address")
    var webAddress: String,
    @SerializedName("username_mobile_card_number")
    var usernameMobileCardNumber: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("notes")
    var notes: String,
    @SerializedName("tag_id")
    var tagId: String?,
)