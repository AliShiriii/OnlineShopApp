package com.example.onlineshopapp.models

data class ServiceResponse<T>(

    val data: List<T>? = null,
    var message: String? = null,
    var status: String? = null,
    var totalCount: Long? = null,
)