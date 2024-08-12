package com.example.route.data.api.model.wish_list

import com.example.route.domain.model.WishlistItem
import com.google.gson.annotations.SerializedName

data class WishlistItemDto(
    @field:SerializedName("imageCover")
    val imageCover: String? = null,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("price")
    val price: Int? = null,
    @field:SerializedName("priceAfterDiscount")
    val priceAfterDiscount: Int? = null,
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("slug")
    val slug: String? = null,
) {
    fun toWishlistItem(): WishlistItem {
        return WishlistItem(
            imageCover = imageCover,
            title = title,
            price = price,
            priceAfterDiscount = priceAfterDiscount,
            id = id,
            slug = slug,
        )
    }
}
