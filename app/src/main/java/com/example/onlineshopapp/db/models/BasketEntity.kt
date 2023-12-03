package com.example.onlineshopapp.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.onlineshopapp.models.products.ProductSize

@Entity
data class BasketEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var productId: Long,
    var quantity: Int,
    var colorId: Long,
    var sizeId: Long,
    val image: String?,
    val price: Long?,
    val title: String?,
    val colorHex: String,
    val size: String,
)