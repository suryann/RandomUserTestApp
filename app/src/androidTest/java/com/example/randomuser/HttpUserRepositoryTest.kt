package com.example.randomuser

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.randomuser.data.repositories.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author Soorianarayanan
 */
@RunWith(AndroidJUnit4::class)
class HttpUserRepositoryTest {
    private lateinit var repository: UserRepository

    @Before
    fun setupRepository() {
        repository = UserRepository()
    }


    @Test
    fun testGetUserList() {
        val userList = repository.fetchUsers(10)
        Assert.assertNotNull(userList)
        Assert.assertEquals(10, userList.results.size)
    }

//    @Test
//    fun testGetUserById() = runBlocking{
//        val userList = repository.fetchAllUsers(10)
//        val userId = userList[0].id
//        val user = repository.getUserById(userList, userId)
//        Assert.assertNotNull(user)
//        Assert.assertEquals(userId, user?.id)
//    }
}