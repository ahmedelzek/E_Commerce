package com.example.route.domain.contract.category

import com.example.route.domain.common.Resource
import com.example.route.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepo {

    suspend fun getCategories(): Flow<Resource<List<Category>?>>
}