package com.summer.passwordcompose.di

import android.content.SharedPreferences
import com.summer.passwordcompose.data.dao.AppDao
import com.summer.passwordcompose.repositories.FileRepository
import com.summer.passwordcompose.repositories.FileRepositoryImpl
import com.summer.passwordcompose.repositories.LocalRepository
import com.summer.passwordcompose.repositories.LocalRepositoryImpl
import com.summer.passwordcompose.repositories.UserRepository
import com.summer.passwordcompose.repositories.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideFileRepository():
            FileRepository = FileRepositoryImpl()

    @Provides
    @Singleton
    fun provideLocalRepository(appDao: AppDao):
            LocalRepository = LocalRepositoryImpl(appDao)

    @Provides
    @Singleton
    fun provideUserRepository(sharedPreferences: SharedPreferences):
            UserRepository = UserRepositoryImpl(sharedPreferences)
}