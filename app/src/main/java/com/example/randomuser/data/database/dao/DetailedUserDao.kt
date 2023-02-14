package com.example.randomuser.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomuser.data.database.entity.DetailedUser


/**
 * @author Soorianarayanan
 */
@Dao
interface DetailedUserDao {

    @Query("SELECT * FROM USER_DETAIL where userName= :name LIMIT 1")
    fun getuser(name: String): DetailedUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(detailedUser: Collection<DetailedUser>)
}