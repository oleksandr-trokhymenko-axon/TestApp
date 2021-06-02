package com.axon.testapp.api

import com.axon.testapp.data.User

data class UsersResponse(
    val results: List<User>
)