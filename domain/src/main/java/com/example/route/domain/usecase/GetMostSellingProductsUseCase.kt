package com.example.route.domain.usecase

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.product.ProductsRepo
import com.example.route.domain.contract.product.SortBy
import com.example.route.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMostSellingProductsUseCase @Inject constructor(private val productsRepo: ProductsRepo) {

    suspend fun invoke(): Flow<Resource<List<Product>?>> {
        return productsRepo.getProducts(
            sortBy = SortBy.MOST_SELLING
        )
    }
}