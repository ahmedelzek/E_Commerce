package com.example.route.domain.usecase.product

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.product.ProductsRepo
import com.example.route.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val productsRepo: ProductsRepo) {
    suspend fun invoke(categoryId: String?): Flow<Resource<List<Product>?>> {
        return productsRepo.getProducts(
            category = categoryId
        )
    }
}