package com.example.randomuser.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomuser.data.model.Picture


/**
 * @author Soorianarayanan
 */
@Entity(tableName = "random_user")
data class RandomUser(
    @PrimaryKey val userName: String,
    @ColumnInfo val firstName: String,
    @ColumnInfo val thumbPicture: String
) {
    override fun toString(): String {
        return "$userName : $firstName : $thumbPicture"
    }
}
