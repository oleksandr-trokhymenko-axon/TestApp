package com.axon.testapp.api

import com.axon.testapp.data.Users
import retrofit2.http.GET

interface UsersApi {
    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }

    @GET("api/")
    suspend fun getUsers(): List<Users>
}