package com.example.route.domain.usecase.auth

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.auth.AuthRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VerifyResetCodeUseCase
    @Inject
    constructor(private val authRepo: AuthRepo) {
        suspend operator fun invoke(resetCode: String): Flow<Resource<String?>> {
            return authRepo.verifyResetCode(resetCode)
        }
    }
