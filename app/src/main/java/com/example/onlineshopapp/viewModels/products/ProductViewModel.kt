package com.example.onlineshopapp.viewModels.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.products.Product
import com.example.onlineshopapp.models.products.ProductColor
import com.example.onlineshopapp.repositoris.products.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    fun getProduct(onResponse: (ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = productRepository.getProduct()
            onResponse(response)
        }
    }

    fun getProductById(id: Long, onResponse: (ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = productRepository.getProductById(id)
            onResponse(response)
        }
    }

    fun getNewProduct(onResponse: (ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = productRepository.getNewProduct()
            onResponse(response)
        }
    }

    fun getPopularProduct(onResponse: (ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = productRepository.getPopularProduct()
            onResponse(response)
        }
    }

}