package com.example.route.data.datasource.wish_list

import com.example.route.data.api.web_services.WishlistWebServices
import com.example.route.data.contract.WishListOnlineDataSource
import com.example.route.data.executeApi
import com.example.route.domain.model.WishlistItem
import com.example.route.domain.model.WishlistResponse
import javax.inject.Inject

class WishListOnlineDataSourceImpl
@Inject
constructor(
    private val webServices: WishlistWebServices,
) : WishListOnlineDataSource {
    override suspend fun addProductToWishlist(
        token: String,
        productId: String,
    ): WishlistResponse<List<String>?> {
        val response = executeApi { webServices.addProductToWishlist(token, productId) }
        return WishlistResponse(response.status, response.message, response.data)
    }

    override suspend fun deleteProductFromWishlist(
        token: String,
        productId: String,
    ): WishlistResponse<List<String>?> {
        val response = executeApi { webServices.removeProductFromWishlist(token, productId) }
        return WishlistResponse(response.status, response.message, response.data)
    }

    override suspend fun getLoggedUserWishlist(token: String): List<WishlistItem>? {
        val response = executeApi { webServices.getLoggedUserWishlist(token) }

        return response.data?.map {
            it.toWishlistItem()
        }
    }
}
