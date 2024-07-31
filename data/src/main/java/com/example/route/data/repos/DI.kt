package com.example.route.data.repos

import com.example.route.data.repos.category.CategoriesRepoImpl
import com.example.route.data.repos.product.ProductRepoImpl
import com.example.route.domain.contract.category.CategoriesRepo
import com.example.route.domain.contract.product.ProductRepo
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
    ): ProductRepo

}