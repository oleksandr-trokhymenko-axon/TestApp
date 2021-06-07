package com.axon.testapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.axon.testapp.data.paging.UsersPagingDataSource
import com.axon.testapp.data.remote.UserService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userService: UserService
) {
    fun getUsers() =
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { UsersPagingDataSource(userService) }
        ).liveData
}