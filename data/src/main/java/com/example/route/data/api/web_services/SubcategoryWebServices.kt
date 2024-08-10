package com.example.route.data.api.web_services

import com.example.route.data.api.model.Response
import com.example.route.data.api.model.subcategory.SubcategoryDto
import retrofit2.http.GET

interface SubcategoryWebServices {
    @GET("api/v1/subcategories")
    suspend fun getAllSubcategories(): Response<List<SubcategoryDto?>?>
}
