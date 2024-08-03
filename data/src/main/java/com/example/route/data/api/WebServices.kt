package com.example.route.data.api

import com.example.route.data.api.model.Response
import com.example.route.data.api.model.categroy.CategoryDto
import com.example.route.data.api.model.product.ProductDto
import com.example.route.data.api.model.subcategory.SubcategoryDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

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