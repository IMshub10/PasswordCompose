package com.summer.passwordcompose.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.summer.passwordcompose.utils.AppUtils

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    var id: String = AppUtils.generateXid(),

    @ColumnInfo("created_at_app")
    var createdAtApp: Long = 0,
    @ColumnInfo("updated_at_app")
    var updatedAtApp: Long = 0,

    @ColumnInfo("name")
    var name: String = "",
    @ColumnInfo("description")
    var description: String? = null,
) {
    @Ignore
    var isSelected = false
}