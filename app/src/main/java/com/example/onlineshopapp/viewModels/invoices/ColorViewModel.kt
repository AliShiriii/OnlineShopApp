package com.example.onlineshopapp.viewModels.invoices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.products.ProductColor
import com.example.onlineshopapp.repositoris.products.ColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(private val colorRepository: ColorRepository) :
    ViewModel() {

    fun getColor(onResponse: (ServiceResponse<ProductColor>) -> Unit) {
        viewModelScope.launch {
            var response = colorRepository.getColor()
            onResponse(response)
        }
    }

    fun getProductById(id: Long, onResponse: (ServiceResponse<ProductColor>) -> Unit) {
        viewModelScope.launch {
            var response = colorRepository.getProductById(id)
            onResponse(response)
        }
    }
}