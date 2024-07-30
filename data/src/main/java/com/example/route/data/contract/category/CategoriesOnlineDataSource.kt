package com.example.route.data.contract.category

import com.example.route.domain.model.Category

interface CategoriesOnlineDataSource {

    suspend fun getAllCategories(): List<Category>?
}