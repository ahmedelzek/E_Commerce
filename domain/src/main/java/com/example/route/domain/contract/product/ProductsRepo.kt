package com.example.route.domain.contract.product

import com.example.route.domain.common.Resource
import com.example.route.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepo {
    suspend fun getProducts(
        sortBy: SortBy? = null,
        category: String? = null,
        brandId: String? = null
    ): Flow<Resource<List<Product>?>>
}

