package com.axon.testapp.data.entities

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("picture")
    val picture: UserPicture,
    @SerializedName("name")
    val name: UserName,
    @SerializedName("location")
    val location: UserLocation,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("login")
    val login: UserLogin,
    @SerializedName("dob")
    val dob: UserDob,
    @SerializedName("phone")
    val phone: String
) {
    data class UserPicture(
        @SerializedName("large")
        val large: String,
        @SerializedName("medium")
        val medium: String,
        @SerializedName("thumbnail")
        val thumbnail: String
    )

    data class UserName(
        @SerializedName("first")
        val first: String,
        @SerializedName("last")
        val last: String
    )

    data class UserLocation(
        @SerializedName("country")
        val country: String,
        @SerializedName("city")
        val city: String
    )

    data class UserLogin(
        @SerializedName("uuid")
        val uuid: String
    )

    data class UserDob(
        @SerializedName("date")
        val date: String,
        @SerializedName("age")
        val age: Int
    )
}