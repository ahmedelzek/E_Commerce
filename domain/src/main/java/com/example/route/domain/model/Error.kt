package com.example.route.domain.model

data class Error(
    val msg: String? = null,
    val param: String? = null,
    val location: String? = null,
    val value: String? = null,
)
