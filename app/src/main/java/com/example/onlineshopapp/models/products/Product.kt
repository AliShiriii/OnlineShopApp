package com.example.onlineshopapp.models.products

data class Product(
    val id: Long? = null,
    val addData: String? = "",
    val category: ProductCategory? = null,
    val colors: List<ProductColor>? = null,
    val description: String? = "",
    val image: String? = "",
    val price: Long? = 0,
    val sizes: List<ProductSize>? = null,
    val title: String? = "",
    val visitCount: Int? = 0,
)
