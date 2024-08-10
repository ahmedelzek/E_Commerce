package com.example.route.domain.usecase.auth

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.auth.AuthRepo
import com.example.route.domain.model.AuthResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase
    @Inject
    constructor(
        private val authRepo: AuthRepo,
    ) {
        suspend operator fun invoke(
            email: String,
            password: String,
        ): Flow<Resource<AuthResponse?>> {
            return authRepo.signIn(email, password)
        }
    }
