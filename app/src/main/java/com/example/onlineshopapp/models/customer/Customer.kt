package com.example.onlineshopapp.models.customer

data class Customer(
    val id: Long?,
    val address: String?,
    var firstName: String?,
    val lastName: String?,
    val phone: String?,
    val postalCode: String?
    )