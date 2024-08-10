package com.example.route.domain.usecase

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.category.CategoriesRepo
import com.example.route.domain.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: CategoriesRepo) {

    suspend fun invoke(): Flow<Resource<List<Category>?>> {
        return repository.getCategories()
    }
}