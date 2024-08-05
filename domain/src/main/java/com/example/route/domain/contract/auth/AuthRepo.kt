package com.example.route.domain.contract.auth

import com.example.route.domain.common.Resource
import com.example.route.domain.model.AuthResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepo {
    suspend fun signUp(
        userName: String,
        email: String,
        password: String,
        repeatPassword: String,
        phone: String,
    ): Flow<Resource<AuthResponse?>>

    suspend fun signIn(
        email: String,
        password: String,
    ): Flow<Resource<AuthResponse?>>
}
