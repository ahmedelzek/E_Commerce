package com.example.route.domain.usecase.auth

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.auth.AuthRepo
import com.example.route.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateAccountNameUseCase
    @Inject
    constructor(private val authRepo: AuthRepo) {
        suspend operator fun invoke(
            token: String,
            newName: String,
        ): Flow<Resource<User?>> {
            return authRepo.updateAccountUserName(token, newName)
        }
    }
