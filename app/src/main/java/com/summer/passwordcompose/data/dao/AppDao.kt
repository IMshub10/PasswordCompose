package com.summer.passwordcompose.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.summer.passwordcompose.data.entities.PassHistoryEntity
import com.summer.passwordcompose.data.entities.TagEntity
import com.summer.passwordcompose.data.entities.VaultEntity

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertVaultIgnore(vaultEntity: VaultEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaultReplace(vaultEntity: VaultEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVaultReplace(vaultEntities: List<VaultEntity>)

    @Update
    fun updateVault(vaultEntity: VaultEntity)

    @Query("Delete From vaults where id =:id ")
    fun deleteVaultById(id: String)

    @Query("Delete From vaults")
    fun deleteAllVaults()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPassHistoryIgnore(vaultEntity: VaultEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPassHistoryReplace(passHistoryEntity: PassHistoryEntity)

    @Query("Delete From pass_histories")
    fun deleteAllPassHistoryRecords()

    @Query("Select * from tags order by lower(name)")
    fun getAllTagsLive(): LiveData<List<TagEntity>?>

    @Query("Select * from tags order by lower(name)")
    fun getAllTags(): List<TagEntity>

    @Query(
        "Select vaults.*, tags.* from vaults " +
                " left join tags on tags.id = vaults.tag_id " +
                " order by vaults.entity_name, vaults.created_at_app "
    )
    fun getAllVaultsWithTheirTag(): LiveData<Map<VaultEntity, TagEntity?>>

    @Query("Select * from vaults order by created_at_app")
    suspend fun getAllVaults(): List<VaultEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTagIgnore(vaultEntity: TagEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTagReplace(tagEntity: TagEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTagReplace(tagEntities: List<TagEntity>)

    @Query("Select * from vaults where id =:vaultId")
    suspend fun getVaultById(vaultId: String): VaultEntity?

    @Query("Delete from tags where id =:tagId ")
    suspend fun deleteTagById(tagId: String)

    @Query("Select * from pass_histories where id =:id ")
    suspend fun getPassHistoryModelById(id: String): PassHistoryEntity?
}