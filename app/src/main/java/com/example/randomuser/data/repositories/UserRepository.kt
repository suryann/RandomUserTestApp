package com.example.randomuser.data.repositories

import android.util.Log
import com.example.randomuser.data.exceptions.GenericException
import com.example.randomuser.data.exceptions.NetworkException
import com.example.randomuser.data.model.Result
import com.example.randomuser.data.model.User
import com.example.randomuser.data.repositories.api.UserService
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author Soorianarayanan
 */
@Singleton
class UserRepository @Inject constructor() {
    fun fetchUsers(size: Int): User {
        Log.v(UserRepository::class.java.simpleName, "check fetch")
        val userService = UserService.create()
        val request = userService.getUsers(size)
        val response = request.execute()
        if (!response.isSuccessful) {
            throw NetworkException()
        }
        return response.body() ?: throw GenericException()
    }
}