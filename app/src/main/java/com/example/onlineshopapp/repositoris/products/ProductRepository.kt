package com.example.onlineshopapp.repositoris.products

import com.example.onlineshopapp.api.products.ProductApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.products.Product
import com.example.onlineshopapp.models.products.ProductCategory
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductRepository @Inject constructor(private val productApi: ProductApi) {


    suspend fun getProduct(): ServiceResponse<Product> {

        return try {
            productApi.getProduct()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getProductById(id: Long): ServiceResponse<Product> {

        return try {
            productApi.getProductById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getNewProduct(): ServiceResponse<Product> {

        return try {
            productApi.getNewProduct()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getPopularProduct(): ServiceResponse<Product> {

        return try {
            productApi.getPopularProduct()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }


}