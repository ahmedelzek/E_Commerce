package com.example.route.data.contract

import com.example.route.domain.model.AuthResponse

interface AuthOnlineDataSource {
    suspend fun signUp(
        userName: String,
        email: String,
        password: String,
        repeatPassword: String,
        phone: String,
    ): AuthResponse?

    suspend fun signIn(
        email: String,
        password: String,
    ): AuthResponse?

}
