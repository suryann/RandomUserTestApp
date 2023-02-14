package com.example.randomuser.data.database

import android.content.Context
import androidx.room.*
import com.example.randomuser.data.database.dao.DetailedUserDao
import com.example.randomuser.data.database.dao.UserDao
import com.example.randomuser.data.database.entity.DetailedUser
import com.example.randomuser.data.database.entity.RandomUser


/**
 * @author Soorianarayanan
 */
@Database(entities = [RandomUser::class, DetailedUser::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun detailedUserDao(): DetailedUserDao

    companion object {
        private const val DB_NAME = "random_user"

        @Volatile
        private var instance: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME).build()
        }
    }

}