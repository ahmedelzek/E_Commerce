package com.example.route.domain.contract.auth

import com.example.route.domain.common.Resource
import com.example.route.domain.model.AuthResponse
import com.example.route.domain.model.User
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

    suspend fun updateAccountUserName(
        token: String,
        newName: String,
    ): Flow<Resource<User?>>

    suspend fun updateAccountPassword(
        token: String,
        currentPassword: String,
        newPassword: String,
        confirmPassword: String,
    ): Flow<Resource<AuthResponse?>>

    suspend fun forgetPassword(email: String): Flow<Resource<String?>>

    suspend fun verifyResetCode(resetCode: String): Flow<Resource<String?>>

    suspend fun resetPassword(
        email: String,
        newPassword: String,
    ): Flow<Resource<String?>>
}
