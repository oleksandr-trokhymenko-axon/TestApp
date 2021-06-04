package com.axon.testapp.data.entities

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("seed")
    val seed: String,
    @SerializedName("result")
    val result: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("version")
    val version: String
)