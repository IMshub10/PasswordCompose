package com.summer.passwordcompose.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.summer.passwordcompose.utils.AppUtils

@Entity(
    tableName = "vaults", foreignKeys = [ForeignKey(
        entity = TagEntity::class,
        childColumns = ["tag_id"],
        parentColumns = ["id"],
        onDelete = ForeignKey.SET_NULL,
    )]
)
data class VaultEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    var id: String = AppUtils.generateXid(),

    @ColumnInfo("created_at_app")
    var createdAtApp: Long = 0,
    @ColumnInfo("updated_at_app")
    var updatedAtApp: Long = 0,

    @ColumnInfo("entity_name")
    var entityName: String = "",
    @ColumnInfo("web_address")
    var webAddress: String = "",
    @ColumnInfo("username_mobile_card_number")
    var usernameMobileCardNumber: String = "",
    @ColumnInfo("password")
    var password: String = "",
    @ColumnInfo("notes")
    var notes: String = "",
    @ColumnInfo("tag_id")
    var tagId: String? = null,
) {
    @Ignore
    var tagEntity: TagEntity? = null

    @Ignore
    var passwordVisible: Boolean = false

    @Ignore
    var isHidden: Boolean = true

}