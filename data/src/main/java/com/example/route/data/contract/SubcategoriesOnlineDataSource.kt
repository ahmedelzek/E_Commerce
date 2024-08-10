package com.example.route.data.contract

import com.example.route.domain.model.Subcategory

interface SubcategoriesOnlineDataSource {
    suspend fun getSubcategories(): List<Subcategory>?
}