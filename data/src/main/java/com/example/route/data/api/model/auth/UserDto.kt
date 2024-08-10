package com.example.route.data.api.model.auth

import com.example.route.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @field:SerializedName("role")
    val role: String? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("email")
    val email: String? = null,
) {
    fun toUser(): User {
        return User(role, name, email)
    }
}
