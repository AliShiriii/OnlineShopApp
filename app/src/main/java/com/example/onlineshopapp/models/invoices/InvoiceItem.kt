package com.example.onlineshopapp.models.invoices

import com.example.onlineshopapp.db.models.BasketEntity
import com.example.onlineshopapp.models.products.Product

data class InvoiceItem(
    val id: Long? = null,
    val product: Product?,
    val quantity: Int?,
    val unitPrice: Long? = 0,
) {
    companion object{
        fun convertFromBasket(basketEntity: BasketEntity): InvoiceItem {
            return InvoiceItem(
                id = null,
                product = Product(id = basketEntity.id),
                quantity = basketEntity.quantity
            )
        }
    }
}