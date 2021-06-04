package com.axon.testapp.ui.users

import androidx.lifecycle.*
import com.axon.testapp.data.entities.User
import com.axon.testapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    init {
        getAllUsers()
    }

    private val _users = MutableSharedFlow<List<User>>()
    var users = _users.asSharedFlow()

    private fun getAllUsers() {
        viewModelScope.launch {
            _users.emit(repository.getUsers(100))
        }
    }
}
