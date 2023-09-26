package com.summer.passwordcompose.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.summer.passwordcompose.utils.AppUtils

@Entity(tableName = "pass_histories")
data class PassHistoryEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    var id: String = AppUtils.generateXid(),

    @ColumnInfo("created_at_app")
    var createdAtApp: Long = 0,
    @ColumnInfo("updated_at_app")
    var updatedAtApp: Long = 0,

    @ColumnInfo("password")
    var password: String,
    @ColumnInfo("has_lower_alphas")
    var hasLowerAlphas: Boolean,
    @ColumnInfo("has_upper_alphas")
    var hasUpperAlphas: Boolean,
    @ColumnInfo("has_numbers")
    var hasNumbers: Boolean,
    @ColumnInfo("has_special_characters")
    var hasSpecialCharacters: Boolean
)