package com.example.route.domain.common

data class AuthError(
    val error: String? = null,
    val ex: Throwable,
) : Throwable(error, ex)
