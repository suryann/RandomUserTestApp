package com.example.randomuser.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomuser.data.database.entity.DetailedUser
import com.example.randomuser.data.database.entity.RandomUser

/**
 * @author Soorianarayanan
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM RANDOM_USER order by userName asc")
    fun getAll(): List<RandomUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: Collection<RandomUser>)

}