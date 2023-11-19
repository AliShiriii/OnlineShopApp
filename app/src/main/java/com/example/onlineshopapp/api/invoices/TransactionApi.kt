package com.example.onlineshopapp.api.invoices

import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoices.PaymentTransaction
import com.example.onlineshopapp.models.products.ProductColor
import com.example.onlineshopapp.models.site.Blog
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TransactionApi {

    @POST("/api/trx/gotoPayment")
    suspend fun goToPayment(@Body data: PaymentTransaction): ServiceResponse<String>
}

