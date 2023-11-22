package com.example.onlineshopapp.modules

import com.example.onlineshopapp.api.customer.UserApi
import com.example.onlineshopapp.api.invoices.InvoiceApi
import com.example.onlineshopapp.api.invoices.TransactionApi
import com.example.onlineshopapp.api.products.ColorApi
import com.example.onlineshopapp.api.products.ProductApi
import com.example.onlineshopapp.api.products.ProductCategoryApi
import com.example.onlineshopapp.api.site.BlogApi
import com.example.onlineshopapp.api.site.ContentApi
import com.example.onlineshopapp.api.site.SliderApi
import com.example.onlineshopapp.config.UnsafeSSLConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModules {

    @Singleton
    @Provides
    fun provideApi(): Retrofit {

        return Retrofit.Builder().baseUrl("https://10.0.2.2:8080/")
            .client(UnsafeSSLConfig.getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideUserApi(): UserApi {
        return provideApi().create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideInvoiceApi(): InvoiceApi {
        return provideApi().create(InvoiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTransactionApi(): TransactionApi {
        return provideApi().create(TransactionApi::class.java)
    }

    @Singleton
    @Provides
    fun provideColorApi(): ColorApi {
        return provideApi().create(ColorApi::class.java)
    }

    @Singleton
    @Provides
    fun provideProductApi(): ProductApi {
        return provideApi().create(ProductApi::class.java)
    }

    @Singleton
    @Provides
    fun provideProductCategoryApi(): ProductCategoryApi {
        return provideApi().create(ProductCategoryApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBlogApi(): BlogApi {
        return provideApi().create(BlogApi::class.java)
    }

    @Singleton
    @Provides
    fun provideContentApi(): ContentApi {
        return provideApi().create(ContentApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSliderApi(): SliderApi {
        return provideApi().create(SliderApi::class.java)
    }
}