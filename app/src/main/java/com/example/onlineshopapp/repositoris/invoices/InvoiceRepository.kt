package com.example.onlineshopapp.repositoris.invoices

import com.example.onlineshopapp.api.invoices.InvoiceApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoices.Invoice
import com.example.onlineshopapp.models.products.ProductColor
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class InvoiceRepository @Inject constructor(private val invoiceApi: InvoiceApi) {

    suspend fun addInvoice(invoice: Invoice, token: String): ServiceResponse<Invoice> {

        return try {
            invoiceApi.addInvoice(invoice, token)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getInvoiceById(id: Long, token: String): ServiceResponse<Invoice> {

        return try {
            invoiceApi.getInvoiceById(id, token)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getInvoiceByUserId(
        id: Long,
        pageIndex: Int,
        pageSize: Int,
        token: String,
    ): ServiceResponse<Invoice> {

        return try {
            invoiceApi.getInvoiceByUserId(id, pageIndex, pageSize, token)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }


}