package com.example.route.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subcategory(
    val id: String? = null,
    val category: String? = null,
    val name: String? = null,
    val slug: String? = null,
) : Parcelable
