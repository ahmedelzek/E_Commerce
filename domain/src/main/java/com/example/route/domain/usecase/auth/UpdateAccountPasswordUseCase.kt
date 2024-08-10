package com.example.route.domain.usecase.auth

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.auth.AuthRepo
import com.example.route.domain.model.AuthResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateAccountPasswordUseCase
    @Inject
    constructor(private val authRepo: AuthRepo) {
        suspend operator fun invoke(
            token: String,
            currentPassword: String,
            newPassword: String,
            confirmPassword: String,
        ): Flow<Resource<AuthResponse?>> {
            return authRepo.updateAccountPassword(
                token,
                currentPassword,
                newPassword,
                confirmPassword,
            )
        }
    }
