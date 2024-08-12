package com.example.route.data.api.web_services

import com.example.route.data.api.model.Response
import com.example.route.data.api.model.product.ProductDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductWebServices {

    @GET("/api/v1/products")
    suspend fun getProducts(
        @Query("sort") sortBy: String? = null,
        @Query("category") categoryId: String? = null,
        @Query("brand") brandId: String? = null
    ): Response<List<ProductDto?>?>
}