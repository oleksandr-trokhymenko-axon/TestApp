package com.axon.testapp.data.remote

import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userService: UserService
) : BaseDataSource() {

//    suspend fun getUsers() = getResult { userService.getAllUsers() }
}