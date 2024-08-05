package com.example.route.data.datasource.auth

import com.example.route.data.api.WebServices
import com.example.route.data.contract.AuthOnlineDataSource
import com.example.route.data.executeAuth
import com.example.route.domain.model.AuthResponse
import javax.inject.Inject

class AuthOnlineDataSourceImpl
@Inject
constructor(
    private val webServices: WebServices,
) : AuthOnlineDataSource {
    override suspend fun signUp(
        userName: String,
        email: String,
        password: String,
        repeatPassword: String,
        phone: String,
    ): AuthResponse {
        val response =
            executeAuth {
                webServices.signUp(
                    userName,
                    email,
                    password,
                    repeatPassword,
                    phone,
                )
            }
        val authResponse = AuthResponse(response.user?.toUser(), response.token)
        return authResponse
    }

    override suspend fun signIn(
        email: String,
        password: String,
    ): AuthResponse {
        val response =
            executeAuth {
                webServices.signIn(email, password)
            }
        val authResponse = AuthResponse(response.user?.toUser(), response.token)
        return authResponse
    }


}
