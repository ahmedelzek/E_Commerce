package com.example.route.domain.contract.wish_list

import com.example.route.domain.common.Resource
import com.example.route.domain.model.WishlistItem
import com.example.route.domain.model.WishlistResponse
import kotlinx.coroutines.flow.Flow

interface WishListRepo {
    suspend fun addProductToWishlist(
        token: String,
        productId: String,
    ): Flow<Resource<WishlistResponse<List<String>?>>>

    suspend fun deleteProductFromWishlist(
        token: String,
        productId: String,
    ): Flow<Resource<WishlistResponse<List<String>?>>>

    suspend fun getLoggedUserWishlist(token: String): Flow<Resource<List<WishlistItem>?>>
}
