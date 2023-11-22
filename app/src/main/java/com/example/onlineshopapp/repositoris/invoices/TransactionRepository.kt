package com.example.onlineshopapp.repositoris.invoices

import com.example.onlineshopapp.api.invoices.TransactionApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoices.PaymentTransaction
import com.example.onlineshopapp.models.invoices.Transaction
import com.example.onlineshopapp.models.products.ProductColor
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TransactionRepository @Inject constructor(private val transactionApi: TransactionApi) {


    suspend fun goToPayment(data: PaymentTransaction): ServiceResponse<String> {

        return try {
            transactionApi.goToPayment(data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}