package com.axon.testapp.data.remote

import com.axon.testapp.data.entities.UserList
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("api/")
    suspend fun getAllUsers(
        @Query("results") result: Int
    ) : UserList
}