package com.example.onlineshopapp.repositoris.invoices

import com.example.onlineshopapp.api.invoices.TransactionApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TransactionRepository @Inject constructor(private val transactionApi: TransactionApi) {
}