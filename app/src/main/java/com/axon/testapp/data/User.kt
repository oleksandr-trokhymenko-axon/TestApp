package com.axon.testapp.data

import com.google.gson.annotations.SerializedName


data class User(
    val picture: UserPicture,
    val name: UserName,
    val gender: String,
    val dob: UserDob,
    val phone: String,
) {

    data class UserPicture(
        val medium: String
    )

    data class UserName(
        val firstName: String,
        val lastName: String
    )

    data class UserDob(
        val date: String
    )
}


