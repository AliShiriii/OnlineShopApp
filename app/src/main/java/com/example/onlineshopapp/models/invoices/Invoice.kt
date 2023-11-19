package com.example.onlineshopapp.models.invoices

import com.example.onlineshopapp.models.customer.User

data class Invoice(
    val id: Long?,
    val addDate: String?,
    val items: List<InvoiceItem>,
    val paymentData: String?,
    val status: String?,
    val transactions: List<Transaction>?,
    val user: User?
)
