package com.example.route.domain.contract.subcategory

import com.example.route.domain.common.Resource
import com.example.route.domain.model.Subcategory
import kotlinx.coroutines.flow.Flow

interface SubcategoriesRepo {

    suspend fun getSubcategories(): Flow<Resource<List<Subcategory>?>>

}