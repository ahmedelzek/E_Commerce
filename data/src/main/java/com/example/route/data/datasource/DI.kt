package com.example.route.data.datasource

import com.example.route.data.contract.category.CategoriesOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CategoriesOnlineDataSourceModule {

    @Binds
    abstract fun bindCategoriesOnlineDataSource(
        categoriesOnlineDataSourceImpl: CategoriesOnlineDataSourceImpl
    ): CategoriesOnlineDataSource

}