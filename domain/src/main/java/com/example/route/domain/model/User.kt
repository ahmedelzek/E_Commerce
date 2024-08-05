package com.example.route.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val role: String? = null,
    val name: String? = null,
    val email: String? = null,
) : Parcelable
