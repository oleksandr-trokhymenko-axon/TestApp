package com.axon.testapp.ui.users

import androidx.lifecycle.*
import androidx.paging.cachedIn
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
    val users = repository.getUsers().cachedIn(viewModelScope)
}
