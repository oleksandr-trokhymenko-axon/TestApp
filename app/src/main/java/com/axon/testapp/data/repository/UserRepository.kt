package com.axon.testapp.data.repository

import com.axon.testapp.data.remote.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService
) {
    suspend fun getUsers(result: Int) = userService.getAllUsers(result).result
}