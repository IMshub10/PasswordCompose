package com.summer.passwordcompose.di

import android.app.Application
import androidx.room.Room
import com.summer.passwordcompose.data.AppDatabase
import com.summer.passwordcompose.data.preferences.Preference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class StorageModule {
    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext application: Application) =
        Preference.createdEncryptedPreference(application)


    @Provides
    @Singleton
    fun providesRoomDB(@ApplicationContext application: Application) =
        Room.databaseBuilder(application, AppDatabase::class.java, "pass_generator_db")
            //.fallbackToDestructiveMigration()
            .createFromAsset("db/init.db")
            .build()

}