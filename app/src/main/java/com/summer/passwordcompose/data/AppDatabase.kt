package com.summer.passwordcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.summer.passwordcompose.data.dao.AppDao
import com.summer.passwordcompose.data.entities.PassHistoryEntity
import com.summer.passwordcompose.data.entities.TagEntity
import com.summer.passwordcompose.data.entities.VaultEntity

@Database(
    entities = [ TagEntity::class, VaultEntity::class, PassHistoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}
