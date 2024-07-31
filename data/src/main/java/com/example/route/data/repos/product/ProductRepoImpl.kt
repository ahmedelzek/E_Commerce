package com.example.route.data.repos.product

import com.example.route.data.contract.ProductsOnlineDataSource
import com.example.route.data.toFlow
import com.example.route.domain.common.Resource
import com.example.route.domain.contract.product.ProductRepo
import com.example.route.domain.contract.product.SortBy
import com.example.route.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepoImpl@Inject constructor(val onlineDataSource: ProductsOnlineDataSource): ProductRepo {
    override suspend fun getProducts(
        sortBy: SortBy?,
        category: String?,
        brandId: String?
    ): Flow<Resource<List<Product>?>> {
        return toFlow {
            onlineDataSource.getProducts(sortBy,category,brandId)
        }
    }
}