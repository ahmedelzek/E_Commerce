package com.example.route.domain.usecase

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.product.ProductRepo
import com.example.route.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val productRepo: ProductRepo) {
    suspend fun invoke(categoryId: String?): Flow<Resource<List<Product>?>> {
        return productRepo.getProducts(
            category = categoryId
        )
    }
}