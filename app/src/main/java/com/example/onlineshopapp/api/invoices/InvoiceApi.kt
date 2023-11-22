package com.example.onlineshopapp.api.invoices

import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoices.Invoice
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface InvoiceApi {

    @POST("/api/invoice/")
    suspend fun addInvoice(
        @Body invoice: Invoice,
        @Header("Authorization") token: String,
    ): ServiceResponse<Invoice>

    @GET("/api/invoice/{id}")
    suspend fun getInvoiceById(
        @Path("id") id: Long,
        @Header("Authorization") token: String,
    ): ServiceResponse<Invoice>

    @GET("/api/invoice/user/{userid}")
    suspend fun getInvoiceByUserId(
        @Path("userid") id: Long,
        @Query("pageIndex") pageIndex: Int,
        @Path("pageSize") pageSize: Int,
        @Header("Authorization") token: String,
    ): ServiceResponse<Invoice>


}