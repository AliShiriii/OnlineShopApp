package com.example.onlineshopapp.repositoris.products

import com.example.onlineshopapp.api.products.ProductApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ProductRepository @Inject constructor(private val productApi: ProductApi) {
}