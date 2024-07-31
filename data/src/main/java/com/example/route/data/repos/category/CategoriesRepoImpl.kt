package com.example.route.data.repos.category

import com.example.route.data.contract.CategoriesOnlineDataSource
import com.example.route.data.toFlow
import com.example.route.domain.common.Resource
import com.example.route.domain.contract.category.CategoriesRepo
import com.example.route.domain.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepoImpl @Inject constructor(private val onlineDataSource: CategoriesOnlineDataSource): CategoriesRepo {

     override suspend fun getCategories(): Flow<Resource<List<Category>?>> {
       return toFlow {
            onlineDataSource.getAllCategories()
        }
    }
}