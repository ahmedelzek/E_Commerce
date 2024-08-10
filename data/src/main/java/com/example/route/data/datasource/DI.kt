package com.example.route.data.datasource

import com.example.route.data.contract.AuthOnlineDataSource
import com.example.route.data.contract.CategoriesOnlineDataSource
import com.example.route.data.contract.ProductsOnlineDataSource
import com.example.route.data.contract.SubcategoriesOnlineDataSource
import com.example.route.data.contract.WishListOnlineDataSource
import com.example.route.data.datasource.auth.AuthOnlineDataSourceImpl
import com.example.route.data.datasource.category.CategoriesOnlineDataSourceImpl
import com.example.route.data.datasource.product.ProductsOnlineDataSourceImpl
import com.example.route.data.datasource.subcategory.SubcategoriesOnlineDataSourceImpl
import com.example.route.data.datasource.wish_list.WishListOnlineDataSourceImpl
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

    @Binds
    abstract fun bindSubcategoriesOnlineDataSource(
        subcategoriesOnlineDataSourceImpl: SubcategoriesOnlineDataSourceImpl
    ): SubcategoriesOnlineDataSource

    @Binds
    abstract fun bindAuthOnlineDataSource(
        authOnlineDataSourceImpl: AuthOnlineDataSourceImpl
    ): AuthOnlineDataSource

    @Binds
    abstract fun bindWishListOnlineDataSource(
        wishListOnlineDataSourceImpl: WishListOnlineDataSourceImpl
    ): WishListOnlineDataSource


}