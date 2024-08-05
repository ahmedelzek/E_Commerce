package com.example.route.domain.usecase.auth

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.auth.AuthRepo
import com.example.route.domain.model.AuthResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegistrationUseCase
    @Inject
    constructor(
        private val authRepo: AuthRepo,
    ) {
        suspend operator fun invoke(
            username: String,
            email: String,
            password: String,
            rePassword: String,
            phone: String,
        ): Flow<Resource<AuthResponse?>> {
            return authRepo.signUp(username, email, password, rePassword, phone)
        }
    }
