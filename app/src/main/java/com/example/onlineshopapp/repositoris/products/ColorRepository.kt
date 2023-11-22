package com.example.onlineshopapp.repositoris.products

import com.example.onlineshopapp.api.products.ColorApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.products.ProductColor
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ColorRepository @Inject constructor(private val colorApi: ColorApi) {

    suspend fun getColor(): ServiceResponse<ProductColor> {

        return try {
            colorApi.getColor()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getProductById(id: Long): ServiceResponse<ProductColor> {

        return try {
            colorApi.getColorById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}