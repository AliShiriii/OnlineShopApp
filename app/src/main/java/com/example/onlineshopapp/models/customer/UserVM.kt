package com.example.onlineshopapp.models.customer

data class UserVM(
    var id: Long?,
    var customerId: Long?,
    var firstName: String?,
    var lastName: String?,
    var address: String?,
    var oldPassword: String?,
    var password: String?,
    var phone: String?,
    var postalCode: String?,
    var repeatPassword: String?,
    var token: String?,
    var username: String?,
    
    )
