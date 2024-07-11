package com.example.route.data.api

import com.example.route.data.api.model.Response
import com.example.route.data.api.model.categroy.CategoryDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface WebServices {

    @GET("/api/v1/categories")
    suspend fun getCategories(): Response<List<CategoryDto?>?>
}