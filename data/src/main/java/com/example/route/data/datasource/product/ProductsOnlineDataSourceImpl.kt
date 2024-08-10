package com.example.route.data.datasource.product

import com.example.route.data.api.web_services.ProductWebServices
import com.example.route.data.contract.ProductsOnlineDataSource
import com.example.route.data.executeApi
import com.example.route.domain.contract.product.SortBy
import com.example.route.domain.model.Product
import javax.inject.Inject

class ProductsOnlineDataSourceImpl @Inject constructor(private val webService: ProductWebServices): ProductsOnlineDataSource {
    override suspend fun getProducts(
        sortBy: SortBy?,
        category: String?,
        brandId: String?): List<Product>? {
       val data = executeApi {
            webService.getProducts(
                sortBy = sortBy?.value,
                categoryId = category,
                brandId = brandId
            )
        }.data?.filterNotNull()?.map {
            it.toProduct()
        }
        return data
    }
}