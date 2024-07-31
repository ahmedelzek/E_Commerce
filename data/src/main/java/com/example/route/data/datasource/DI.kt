package com.example.route.data.datasource

import com.example.route.data.contract.CategoriesOnlineDataSource
import com.example.route.data.contract.ProductsOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class OnlineDataSourceModule {

    @Binds
    abstract fun bindCategoriesOnlineDataSource(
        categoriesOnlineDataSourceImpl: CategoriesOnlineDataSourceImpl
    ): CategoriesOnlineDataSource

    @Binds
    abstract fun bindProductsOnlineDataSource(
        productsOnlineDataSourceImpl: ProductsOnlineDataSourceImpl
    ): ProductsOnlineDataSource

}