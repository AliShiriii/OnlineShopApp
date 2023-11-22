package com.example.onlineshopapp.repositoris.products

import com.example.onlineshopapp.api.products.ProductCategoryApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.products.ProductCategory
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductCategoryRepository @Inject constructor(private val productCategoryApi: ProductCategoryApi) {

    suspend fun getCategory(): ServiceResponse<ProductCategory> {

        return try {
            productCategoryApi.getCategory()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getCategoryById(id: Long): ServiceResponse<ProductCategory> {

        return try {
            productCategoryApi.getCategoryById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

}