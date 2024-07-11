package com.example.route.data.contract.category

import com.example.route.domain.common.Resource
import com.example.route.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesOnlineDataSource {

    suspend fun getAllCategories(): List<Category>?
}