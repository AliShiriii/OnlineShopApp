package com.example.onlineshopapp.api.products

import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.products.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("/api/product")
    suspend fun getProduct(): ServiceResponse<Product>

    @GET("/api/product/{id}")
    suspend fun getProductById(@Path("id") id: Long): ServiceResponse<Product>

}