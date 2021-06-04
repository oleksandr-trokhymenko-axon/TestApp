package com.axon.testapp.data.entities

import com.google.gson.annotations.SerializedName

data class UserList(
    @SerializedName("results")
    val results: List<User>
)