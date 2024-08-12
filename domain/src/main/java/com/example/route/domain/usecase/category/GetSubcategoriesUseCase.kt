package com.example.route.domain.usecase.category

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.subcategory.SubcategoriesRepo
import com.example.route.domain.model.Subcategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSubcategoriesUseCase@Inject constructor(private val repo: SubcategoriesRepo)  {

    suspend fun invoke(): Flow<Resource<List<Subcategory>?>> {
         return repo.getSubcategories()
    }
}