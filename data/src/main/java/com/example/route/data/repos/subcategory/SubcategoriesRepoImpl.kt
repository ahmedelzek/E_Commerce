package com.example.route.data.repos.subcategory

import com.example.route.data.contract.SubcategoriesOnlineDataSource
import com.example.route.data.toFlow
import com.example.route.domain.common.Resource
import com.example.route.domain.contract.subcategory.SubcategoriesRepo
import com.example.route.domain.model.Subcategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubcategoriesRepoImpl @Inject constructor(private val subcategoriesOnlineDataSource: SubcategoriesOnlineDataSource) :
    SubcategoriesRepo {
    override suspend fun getSubcategories(): Flow<Resource<List<Subcategory>?>> {
        return toFlow { subcategoriesOnlineDataSource.getSubcategories() }

    }
}