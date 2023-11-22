package com.example.onlineshopapp.repositoris.invoices

import com.example.onlineshopapp.api.invoices.InvoiceApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class InvoiceRepository @Inject constructor(private val invoiceApi: InvoiceApi) {
}