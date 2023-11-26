package com.example.onlineshopapp.models.products

data class Product(
    val id: Long?,
    val addData: String,
    val category: ProductCategory?,
    val colors: List<ProductColor>?,
    val description: String?,
    val image: String?,
    val price: Long?,
    val sizes: List<ProductSize>?,
    val title: String?,
    val visitCount: Int?
)
