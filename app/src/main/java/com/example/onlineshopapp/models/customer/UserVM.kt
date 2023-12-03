package com.example.onlineshopapp.models.customer

data class UserVM(
    var id: Long? = null,
    var customerId: Long? = null,
    var firstName: String?,
    var lastName: String?,
    var address: String?,
    var oldPassword: String? = null,
    var password: String?,
    var phone: String?,
    var postalCode: String?,
    var repeatPassword: String? = null,
    var token: String? = null,
    var username: String?,

    )
