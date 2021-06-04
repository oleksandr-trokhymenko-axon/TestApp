package com.axon.testapp.data.entities

import com.google.gson.annotations.SerializedName

data class UserList(
    @SerializedName("info")
    val info: Info,
    @SerializedName("result")
    val result: List<User>
)