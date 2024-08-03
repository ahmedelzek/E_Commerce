package com.example.route.data.datasource.category

import com.example.route.data.api.WebServices
import com.example.route.data.contract.CategoriesOnlineDataSource
import com.example.route.data.executeApi
import com.example.route.domain.model.Category
import javax.inject.Inject

class CategoriesOnlineDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    CategoriesOnlineDataSource {

    override suspend fun getAllCategories(): List<Category>? {

        val response = executeApi { webServices.getCategories() }
        return response.data?.filterNotNull()?.map {
            it.toCategory()
        }
    }
}

