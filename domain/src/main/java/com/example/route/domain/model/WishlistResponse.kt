package com.example.route.domain.model

data class WishlistResponse<T>(
    val status: String? = null,
    val message: String? = null,
    val data: T? = null,
)
