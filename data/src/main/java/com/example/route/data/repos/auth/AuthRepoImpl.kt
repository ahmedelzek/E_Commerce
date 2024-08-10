package com.example.route.data.repos.auth

import com.example.route.data.contract.AuthOnlineDataSource
import com.example.route.data.toFlow
import com.example.route.domain.common.Resource
import com.example.route.domain.contract.auth.AuthRepo
import com.example.route.domain.model.AuthResponse
import com.example.route.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepoImpl
@Inject
constructor(
    private val authOnlineDataSource: AuthOnlineDataSource,
) : AuthRepo {
    override suspend fun signUp(
        userName: String,
        email: String,
        password: String,
        repeatPassword: String,
        phone: String,
    ): Flow<Resource<AuthResponse?>> {
        return toFlow {
            authOnlineDataSource.signUp(userName, email, password, repeatPassword, phone)
        }
    }

    override suspend fun signIn(
        email: String,
        password: String,
    ): Flow<Resource<AuthResponse?>> {
        return toFlow {
            authOnlineDataSource.signIn(email, password)
        }
    }

    override suspend fun updateAccountUserName(
        token: String,
        newName: String,
    ): Flow<Resource<User?>> {
        return toFlow {
            authOnlineDataSource.updateAccountName(token, newName)
        }
    }

    override suspend fun updateAccountPassword(
        token: String,
        currentPassword: String,
        newPassword: String,
        confirmPassword: String,
    ): Flow<Resource<AuthResponse?>> {
        return toFlow {
            authOnlineDataSource.updateAccountPassword(
                token,
                currentPassword,
                newPassword,
                confirmPassword,
            )
        }
    }

    override suspend fun forgetPassword(email: String): Flow<Resource<String?>> {
        return toFlow { authOnlineDataSource.forgetPassword(email) }
    }

    override suspend fun verifyResetCode(resetCode: String): Flow<Resource<String?>> {
        return toFlow { authOnlineDataSource.verifyResetCode(resetCode) }
    }

    override suspend fun resetPassword(
        email: String,
        newPassword: String,
    ): Flow<Resource<String?>> {
        return toFlow { authOnlineDataSource.resetPassword(email, newPassword) }
    }

}
