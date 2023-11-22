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
import com.example.onlineshopapp.repositoris.customer.UserRepository
import com.example.onlineshopapp.repositoris.invoices.InvoiceRepository
import com.example.onlineshopapp.repositoris.invoices.TransactionRepository
import com.example.onlineshopapp.repositoris.products.ColorRepository
import com.example.onlineshopapp.repositoris.products.ProductCategoryRepository
import com.example.onlineshopapp.repositoris.products.ProductRepository
import com.example.onlineshopapp.repositoris.site.BlogRepository
import com.example.onlineshopapp.repositoris.site.ContentRepository
import com.example.onlineshopapp.repositoris.site.SliderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBlogRepository(blogApi: BlogApi) = BlogRepository(blogApi)

    @Singleton
    @Provides
    fun provideContentRepository(contentApi: ContentApi) = ContentRepository(contentApi)

    @Singleton
    @Provides
    fun provideSliderRepository(sliderApi: SliderApi) = SliderRepository(sliderApi)

    @Singleton
    @Provides
    fun provideColorRepository(colorApi: ColorApi) = ColorRepository(colorApi)

    @Singleton
    @Provides
    fun provideProductRepository(productApi: ProductApi) = ProductRepository(productApi)

    @Singleton
    @Provides
    fun provideProductCategoryRepository(productCategoryApi: ProductCategoryApi) =
        ProductCategoryRepository(productCategoryApi)

    @Singleton
    @Provides
    fun provideInvoiceRepository(invoiceApi: InvoiceApi) = InvoiceRepository(invoiceApi)

    @Singleton
    @Provides
    fun provideTransactionRepository(transactionApi: TransactionApi) =
        TransactionRepository(transactionApi)

    @Singleton
    @Provides
    fun provideUserRepository(userApi: UserApi) = UserRepository(userApi)
}