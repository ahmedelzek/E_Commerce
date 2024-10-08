package com.example.route.data.repos

import com.example.route.data.repos.auth.AuthRepoImpl
import com.example.route.data.repos.category.CategoriesRepoImpl
import com.example.route.data.repos.product.ProductRepoImpl
import com.example.route.data.repos.subcategory.SubcategoriesRepoImpl
import com.example.route.data.repos.wish_list.WishListRepoImpl
import com.example.route.domain.contract.auth.AuthRepo
import com.example.route.domain.contract.category.CategoriesRepo
import com.example.route.domain.contract.product.ProductsRepo
import com.example.route.domain.contract.subcategory.SubcategoriesRepo
import com.example.route.domain.contract.wish_list.WishListRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCategoriesRepo(
        categoriesRepoImpl: CategoriesRepoImpl
    ): CategoriesRepo

    @Binds
    abstract fun bindProductRepo(
        productRepoImpl: ProductRepoImpl
    ): ProductsRepo

    @Binds
    abstract fun bindSubcategoriesRepo(
        subcategoriesRepoImpl: SubcategoriesRepoImpl
    ): SubcategoriesRepo

    @Binds
    abstract fun bindAuthRepo(
        authRepoImpl: AuthRepoImpl
    ): AuthRepo

    @Binds
    abstract fun bindWishListRepo(
        wishListRepoImpl: WishListRepoImpl
    ): WishListRepo
}