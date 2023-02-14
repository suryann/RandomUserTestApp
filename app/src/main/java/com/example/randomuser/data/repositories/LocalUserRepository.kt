package com.example.randomuser.data.repositories

import com.example.randomuser.data.database.dao.DetailedUserDao
import com.example.randomuser.data.database.dao.UserDao
import com.example.randomuser.data.database.entity.DetailedUser
import com.example.randomuser.data.database.entity.RandomUser
import javax.inject.Inject


/**
 * @author Soorianarayanan
 */
class LocalUserRepository @Inject constructor(
    private val userDao: UserDao,
    private val detailedUserDao: DetailedUserDao
) {
    fun getRandomUser(): List<RandomUser>{
        return userDao.getAll()
    }

    fun getDetailedUser(userName: String): DetailedUser{
        return detailedUserDao.getuser(userName)
    }

    fun saveUsers(users: List<RandomUser>){
        userDao.insert(users)
    }

    fun saveDetailedUsers(users: List<DetailedUser>){
        detailedUserDao.insert(users)
    }
}