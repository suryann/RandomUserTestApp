package com.example.randomuser.di

import android.content.Context
import com.example.randomuser.data.database.AppDataBase
import com.example.randomuser.data.database.dao.DetailedUserDao
import com.example.randomuser.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author Soorianarayanan
 */
@InstallIn(SingletonComponent::class)
@Module
class DBModuleInjection {

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return AppDataBase.getInstance(context)
    }

    @Provides
    fun provideUserDao(appDataBase: AppDataBase) : UserDao{
        return appDataBase.userDao()
    }

    @Provides
    fun provideDetailedUserDao(appDataBase: AppDataBase): DetailedUserDao{
        return appDataBase.detailedUserDao()
    }
}