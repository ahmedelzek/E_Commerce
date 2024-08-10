package com.example.route.data.api

import com.example.route.data.api.web_services.AuthWebServices
import com.example.route.data.api.web_services.CategoryWebServices
import com.example.route.data.api.web_services.ProductWebServices
import com.example.route.data.api.web_services.SubcategoryWebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor.apply {
            })
            .build()
    }
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://ecommerce.routemisr.com")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
    @Provides
    fun provideAuthWebServices(retrofit: Retrofit): AuthWebServices {
        return retrofit.create(AuthWebServices::class.java)
    }
    @Provides
    fun provideCategoryWebServices(retrofit: Retrofit): CategoryWebServices {
        return retrofit.create(CategoryWebServices::class.java)
    }
    @Provides
    fun provideSubcategoryWebServices(retrofit: Retrofit): SubcategoryWebServices {
        return retrofit.create(SubcategoryWebServices::class.java)
    }

    @Provides
    fun provideProductWebServices(retrofit: Retrofit): ProductWebServices {
        return retrofit.create(ProductWebServices::class.java)
    }

}