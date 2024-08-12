package com.example.route.data.api.web_services

import com.example.route.data.api.model.wish_list.WishlistItemDto
import com.example.route.data.api.model.wish_list.WishlistResponse
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface WishlistWebServices {

    @FormUrlEncoded
    @POST("/api/v1/wishlist")
    suspend fun addProductToWishlist(
        @Header("token") token: String,
        @Field("productId") productId: String,
    ): WishlistResponse<List<String>?>

    @DELETE("/api/v1/wishlist/{productId}")
    suspend fun removeProductFromWishlist(
        @Header("token") token: String,
        @Path("productId") productId: String,
    ): WishlistResponse<List<String>?>

    @GET("/api/v1/wishlist")
    suspend fun getLoggedUserWishlist(
        @Header("token") token: String,
    ): WishlistResponse<List<WishlistItemDto>?>
}
