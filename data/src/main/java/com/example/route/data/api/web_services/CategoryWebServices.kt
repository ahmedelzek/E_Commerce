package com.example.route.data.api.web_services

import com.example.route.data.api.model.Response
import com.example.route.data.api.model.categroy.CategoryDto
import retrofit2.http.GET

interface CategoryWebServices {

    @GET("/api/v1/categories")
    suspend fun getCategories(): Response<List<CategoryDto?>?>
}
