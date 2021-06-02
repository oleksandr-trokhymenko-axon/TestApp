package com.axon.testapp.ui.userslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.axon.testapp.data.User
import com.axon.testapp.data.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val repository: UsersRepository
) : ViewModel() {

    private val _usersListLiveData = MutableLiveData<PagingData<User>>()

    val usersListLiveData: LiveData<PagingData<User>>
        get() = _usersListLiveData

    init {
        getUsers(100)
    }

    fun getUsers(count: Int) {
        viewModelScope.launch {
            val value = repository.getResults(count).value
            if (value != null) {
                _usersListLiveData.value = value
            }
        }
    }
}
