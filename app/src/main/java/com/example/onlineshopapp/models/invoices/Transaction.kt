package com.example.onlineshopapp.models.invoices

data class Transaction(
    val amount: Long?,
    val cardHolder: String?,
    val code: String?,
    val custom: String?,
    val customerPhone: String?,
    val id: Long?,
    val orderId: String?,
    val refId: String?,
    val refundRequest: String?,
    val shaparakRefId: String?,
    val transId: String?
)
