package com.example.randomuser.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuser.data.database.entity.DetailedUser
import com.example.randomuser.data.database.entity.RandomUser
import com.example.randomuser.usecases.RandomUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Soorianarayanan
 */
@HiltViewModel
class RandomUserViewModel @Inject internal constructor(private val useCase: RandomUserUseCase) :
    ViewModel() {
    private val _randomUsers = MutableLiveData<State<List<RandomUser>>>()
    val users: LiveData<State<List<RandomUser>>> get() = _randomUsers

    private val _detailedUser = MutableLiveData<State<DetailedUser>>()
    val user: LiveData<State<DetailedUser>> get() = _detailedUser

    init {
        loadUsers()
    }

    fun loadUsers() {
        _randomUsers.value = State.loading()
        viewModelScope.launch {
            val state = withContext(Dispatchers.IO) {
                try {
                    val users = useCase.getAllRandomUsers()
                    Log.v("ViewModel", "users --> ${users.size}")
                    State.success(users)
                } catch (exception: Exception) {
                    State.error(exception.message)
                }
            }
            _randomUsers.value = state
        }
    }

    fun onUserItemClicked(user: String) {
        _detailedUser.value = State.loading()
        viewModelScope.launch {
            val state = withContext(Dispatchers.IO) {
                try {
                    val user = useCase.getUser(user)
                    State.success(user)
                } catch (exception : Exception) {
                    State.error(exception.message)
                }
            }
            _detailedUser.value = state
        }
    }
}