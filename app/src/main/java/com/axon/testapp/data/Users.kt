package com.axon.testapp.data

import java.io.Serializable

data class Users(
    val links: UserLinks,
    val name: UserName,
    val gender: String,
    val dob: UserDob,
    val email: String,
    val phone: Int
) : Serializable {
    data class UserLinks(
        val large: String,
        val medium: String,
        val thumbnail: String
    ) : Serializable

    data class UserName(
        val firstName: String,
        val lastName: String
    ) : Serializable

    data class UserDob(
        val date: Int
    ) : Serializable
}
