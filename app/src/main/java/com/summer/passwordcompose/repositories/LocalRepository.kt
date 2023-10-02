package com.summer.passwordcompose.repositories

import androidx.lifecycle.LiveData
import com.summer.passwordcompose.data.entities.PassHistoryEntity
import com.summer.passwordcompose.data.entities.TagEntity
import com.summer.passwordcompose.data.entities.VaultEntity

interface LocalRepository {
    //region room db methods
    suspend fun insertIgnoreVaultEntity(vaultEntity: VaultEntity)
    suspend fun insertReplaceVaultEntity(vaultEntity: VaultEntity)
    suspend fun insertPastHistory(passHistoryEntity: PassHistoryEntity)
    suspend fun insertReplaceTagEntity(tagEntity: TagEntity)
    fun getAllTagsLive(): LiveData<List<TagEntity>?>
    suspend fun getAllTags(): List<TagEntity>
    fun getAllVaultsWithTheirTag(): LiveData<Map<VaultEntity, TagEntity?>>
    suspend fun getAllVaults(): List<VaultEntity>
    suspend fun getVaultById(vaultId: String): VaultEntity?
    suspend fun deleteVaultById(vaultId: String)
    suspend fun deleteTagById(tagId: String)
    suspend fun getPassHistoryModel(id: String): PassHistoryEntity?
    //endregion
}