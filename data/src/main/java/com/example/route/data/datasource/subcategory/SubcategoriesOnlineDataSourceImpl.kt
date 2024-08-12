package com.example.route.data.datasource.subcategory

import com.example.route.data.api.web_services.SubcategoryWebServices
import com.example.route.data.contract.SubcategoriesOnlineDataSource
import com.example.route.domain.model.Subcategory
import javax.inject.Inject

class SubcategoriesOnlineDataSourceImpl @Inject constructor(private val webServices: SubcategoryWebServices) :
    SubcategoriesOnlineDataSource {
    override suspend fun getSubcategories(): List<Subcategory>? {
        val response = webServices.getAllSubcategories()
        return response.data?.filterNotNull()?.map {
            it.toSubcategory()
        }
    }
}