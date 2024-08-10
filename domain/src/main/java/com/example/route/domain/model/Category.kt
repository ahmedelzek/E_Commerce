package com.example.route.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Category(
	val image: String? = null,
	val name: String? = null,
	val id: String? = null,
	val slug: String? = null
) : Parcelable
