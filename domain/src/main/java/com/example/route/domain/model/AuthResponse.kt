package com.example.route.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthResponse(
    val user: User? = null,
    val token: String? = null,
) : Parcelable
