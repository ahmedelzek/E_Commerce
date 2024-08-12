package com.example.route.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WishlistItem(
    val imageCover: String? = null,
    val title: String? = null,
    val price: Int? = null,
    val priceAfterDiscount: Int? = null,
    val id: String? = null,
    val slug: String? = null,
) : Parcelable
