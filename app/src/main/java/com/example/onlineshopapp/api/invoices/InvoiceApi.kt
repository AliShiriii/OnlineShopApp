package com.example.onlineshopapp.api.invoices

import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoices.Invoice
import com.example.onlineshopapp.models.products.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path

interface InvoiceApi {

    @GET("/api/invoice/{id}")
    suspend fun getInvoiceById(@Path("id") id: Long): ServiceResponse<Invoice>

    @GET("/api/invoice/user/{userid}")
    suspend fun getInvoiceByUserId(@Path("userid") id: Long): ServiceResponse<Invoice>


}