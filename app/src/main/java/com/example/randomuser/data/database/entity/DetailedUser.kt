package com.example.randomuser.data.database.entity

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author Soorianarayanan
 */
@Entity(tableName = "user_detail")
data class DetailedUser(
    @PrimaryKey val userName: String,
    @ColumnInfo val title: String,
    @ColumnInfo val firstName: String,
    @ColumnInfo val lastName: String,
    @ColumnInfo val email: String,
    @ColumnInfo val phone: String,
    @ColumnInfo val cellPhone: String,
    @ColumnInfo val city: String,
    @ColumnInfo val state: String,
    @ColumnInfo val country: String,
    @ColumnInfo val pic: String,
){
    override fun toString(): String {
        return "$userName : $title : $firstName : $lastName : $email " +
                ": $phone : $cellPhone : $city : $state : $country : $pic"
    }
}

