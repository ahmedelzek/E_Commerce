package com.example.route.data.contract

import com.example.route.domain.contract.product.SortBy
import com.example.route.domain.model.Product

interface ProductsOnlineDataSource {
    suspend fun getProducts(
        sortBy: SortBy?,
        category: String?,
        brandId: String?
    ): List<Product>?
}