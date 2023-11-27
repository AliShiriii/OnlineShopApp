package com.example.onlineshopapp.viewModels.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.products.Product
import com.example.onlineshopapp.models.products.ProductColor
import com.example.onlineshopapp.models.site.Slider
import com.example.onlineshopapp.repositoris.products.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    var dataList = mutableStateOf<List<Product>>(listOf())
    var isLoading = mutableStateOf(false)
    val data = mutableStateOf<Product?>(null)

    init {
        getProducts(
            0, 6
        ) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun getProducts(pageIndex: Int, pageSize: Int, onResponse: (ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = productRepository.getProduct(pageIndex, pageSize)
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
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
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