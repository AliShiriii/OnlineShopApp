package com.example.onlineshopapp.api.invoices

import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoices.Invoice
import com.example.onlineshopapp.models.products.ProductColor
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface InvoiceApi {

    @POST("/api/invoice/")
    suspend fun addInvoice(@Body invoice: Invoice): ServiceResponse<Invoice>

    @GET("/api/invoice/{id}")
    suspend fun getInvoiceById(@Path("id") id: Long): ServiceResponse<Invoice>

    @GET("/api/invoice/user/{userid}")
    suspend fun getInvoiceByUserId(
        @Path("userid") id: Long,
        @Query("pageIndex") pageIndex: Int,
        @Path("pageSize") pageSize: Int,
    ): ServiceResponse<Invoice>


}