package com.axon.testapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }

    @GET("api/")
    suspend fun getUsers(
        @Query("results") result: Int,
        @Query("page") page: Int
    ): UsersResponse
}