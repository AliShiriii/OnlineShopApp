package com.example.onlineshopapp.viewModels.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.products.ProductCategory
import com.example.onlineshopapp.repositoris.products.ProductCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCategoryVieModel @Inject constructor(private val productCategoryRepository: ProductCategoryRepository) :
    ViewModel() {

    fun getColor(onResponse: (ServiceResponse<ProductCategory>) -> Unit) {
        viewModelScope.launch {
            var response = productCategoryRepository.getCategory()
            onResponse(response)
        }
    }

    fun getCategoryById(id: Long, onResponse: (ServiceResponse<ProductCategory>) -> Unit) {
        viewModelScope.launch {
            var response = productCategoryRepository.getCategoryById(id)
            onResponse(response)
        }
    }
}