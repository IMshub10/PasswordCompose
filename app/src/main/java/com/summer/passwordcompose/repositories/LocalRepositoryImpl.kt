package com.summer.passwordcompose.repositories

import androidx.lifecycle.LiveData
import com.summer.passwordcompose.data.dao.AppDao
import com.summer.passwordcompose.data.entities.PassHistoryEntity
import com.summer.passwordcompose.data.entities.TagEntity
import com.summer.passwordcompose.data.entities.VaultEntity
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val dao: AppDao) : LocalRepository {
    override suspend fun insertIgnoreVaultEntity(vaultEntity: VaultEntity) =
        dao.insertVaultIgnore(vaultEntity)

    override suspend fun insertReplaceVaultEntity(vaultEntity: VaultEntity) =
        dao.insertVaultReplace(vaultEntity)

    override suspend fun insertPastHistory(passHistoryEntity: PassHistoryEntity) =
        dao.insertPassHistoryReplace(passHistoryEntity)


    override fun getAllTagsLive(): LiveData<List<TagEntity>?> =
        dao.getAllTagsLive()

    override suspend fun getAllTags(): List<TagEntity> =
        dao.getAllTags()

    override fun getAllVaultsWithTheirTag(): LiveData<Map<VaultEntity, TagEntity?>> =
        dao.getAllVaultsWithTheirTag()


    override suspend fun deleteVaultById(vaultId: String) =
        dao.deleteVaultById(vaultId)

    override suspend fun deleteTagById(tagId: String) = dao.deleteTagById(tagId)

    override suspend fun getPassHistoryModel(id: String) = dao.getPassHistoryModelById(id)

    override suspend fun insertReplaceTagEntity(tagEntity: TagEntity) =
        dao.insertTagReplace(tagEntity)

    override suspend fun getAllVaults() =
        dao.getAllVaults()

    override suspend fun getVaultById(vaultId: String) =
        dao.getVaultById(vaultId)
}