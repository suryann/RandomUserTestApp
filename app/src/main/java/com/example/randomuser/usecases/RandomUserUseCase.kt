package com.example.randomuser.usecases

import android.util.Log
import com.example.randomuser.data.database.entity.DetailedUser
import com.example.randomuser.data.database.entity.RandomUser
import com.example.randomuser.data.exceptions.GenericException
import com.example.randomuser.data.exceptions.NetworkException
import com.example.randomuser.data.repositories.LocalUserRepository
import com.example.randomuser.data.repositories.UserRepository
import com.example.randomuser.ui.MainActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Soorianarayanan
 */
@Singleton
class RandomUserUseCase @Inject constructor(
    private val httpRepository: UserRepository,
    private val localUserRepository: LocalUserRepository
) {
    @kotlin.jvm.Throws(NetworkException::class, GenericException::class)
    fun getAllRandomUsers(): List<RandomUser> {
        try {
            var users = localUserRepository.getRandomUser()
            var detailedUsers: List<DetailedUser> = listOf()
            if (users.isEmpty()) {
                val userMap = httpRepository.fetchUsers(10)
                users = userMap.results.map {
                    RandomUser(
                        it.login.username,
                        it.name.first,
                        it.picture.thumbnail
                    )
                }
                detailedUsers = userMap.results.map {
                    DetailedUser(
                        it.login.username,
                        it.name.title,
                        it.name.first,
                        it.name.last,
                        it.email,
                        it.phone,
                        it.cell,
                        it.location.city,
                        it.location.state,
                        it.location.country,
                        it.picture.large
                    )
                }
                localUserRepository.saveUsers(users)
                localUserRepository.saveDetailedUsers(detailedUsers)
            }
            users.map {
                Log.v(
                    RandomUserUseCase::class.java.simpleName,
                    "it.firstName  -> ${it.firstName}, userName  -> ${it.userName}, pic -> ${it.thumbPicture}"
                )
            }
            return users
        } catch (networkException: NetworkException) {
            throw networkException
        } catch (genericException: Exception) {
            throw GenericException()
        }
    }

    fun getUser(user: String) = localUserRepository.getDetailedUser(user)
}