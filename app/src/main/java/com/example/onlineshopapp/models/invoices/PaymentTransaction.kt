package com.example.onlineshopapp.models.invoices

import com.example.onlineshopapp.models.customer.User

data class PaymentTransaction(var items: List<InvoiceItem>?, var user: User)