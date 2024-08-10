package com.example.route.data.api

import com.example.route.data.api.model.Response
import com.example.route.data.api.model.categroy.CategoryDto
import com.example.route.data.api.model.product.ProductDto
import com.example.route.data.api.model.subcategory.SubcategoryDto
import com.route.data.api.model.auth.AuthResponseDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface WebServices {


    @FormUrlEncoded
    @POST("/api/v1/auth/signup")
    suspend fun signUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("rePassword") rePassword: String,
        @Field("phone") phone: String,
    ): AuthResponseDto

    @FormUrlEncoded
    @POST("/api/v1/auth/signin")
    suspend fun signIn(
        @Field("email") email: String,
        @Field("password") password: String,
    ): AuthResponseDto

    @FormUrlEncoded
    @PUT("/api/v1/users/updateMe")
    suspend fun updateAccountName(
        @Header("token") token: String,
        @Field("name") newName: String,
    ): AuthResponseDto

    @FormUrlEncoded
    @PUT("/api/v1/users/changeMyPassword")
    suspend fun updateAccountPassword(
        @Header("token") token: String,
        @Field("currentPassword") currentPassword: String,
        @Field("password") password: String,
        @Field("rePassword") rePassword: String,
    ): AuthResponseDto

    @FormUrlEncoded
    @POST("/api/v1/auth/forgotPasswords")
    suspend fun forgetPassword(
        @Field("email") email: String,
    ): AuthResponseDto

    @FormUrlEncoded
    @POST("/api/v1/auth/verifyResetCode")
    suspend fun verifyResetCode(
        @Field("resetCode") resetCode: String,
    ): AuthResponseDto

    @FormUrlEncoded
    @PUT("/api/v1/auth/resetPassword")
    suspend fun resetPassword(
        @Field("email") email: String,
        @Field("newPassword") newPassword: String,
    ): AuthResponseDto

    @GET("/api/v1/categories")
    suspend fun getCategories(): Response<List<CategoryDto?>?>

    @GET("/api/v1/products")
    suspend fun getProducts(
        @Query("sort") sortBy: String? = null,
        @Query("category") categoryId: String? = null,
        @Query("brand") brandId: String? = null
    ): Response<List<ProductDto?>?>

    @GET("api/v1/subcategories")
    suspend fun getAllSubcategories(): Response<List<SubcategoryDto?>?>
}