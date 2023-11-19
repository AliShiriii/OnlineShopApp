package com.example.onlineshopapp.models.invoices

import com.example.onlineshopapp.models.products.Product

data class InvoiceItem(
    val id: Long?,
    val product: Product?,
    val quantity: Int?,
    val unitPrice: Long?
)