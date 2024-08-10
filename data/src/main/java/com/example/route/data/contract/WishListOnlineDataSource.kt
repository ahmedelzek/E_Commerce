package com.example.route.data.contract

import com.example.route.domain.model.WishlistItem
import com.example.route.domain.model.WishlistResponse

interface WishListOnlineDataSource {

    suspend fun addProductToWishlist(
        token: String,
        productId: String,
    ): WishlistResponse<List<String>?>

    suspend fun deleteProductFromWishlist(
        token: String,
        productId: String,
    ): WishlistResponse<List<String>?>

    suspend fun getLoggedUserWishlist(token: String): List<WishlistItem>?

}