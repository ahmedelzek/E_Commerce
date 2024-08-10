package com.example.route.domain.usecase.auth

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.auth.AuthRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResetPasswordUseCase
    @Inject
    constructor(private val authRepo: AuthRepo) {
        suspend operator fun invoke(
            email: String,
            newPassword: String,
        ): Flow<Resource<String?>> {
            return authRepo.resetPassword(email, newPassword)
        }
    }
