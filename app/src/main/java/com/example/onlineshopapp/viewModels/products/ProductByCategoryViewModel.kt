package com.example.onlineshopapp.viewModels.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.products.Product
import com.example.onlineshopapp.repositoris.products.ProductRepository
import com.example.onlineshopapp.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductByCategoryViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    private var categoryId: Long = ThisApp.productCategoryId
    var pageSize = 3
    var pageIndex = mutableStateOf(0)

    var dataList = mutableStateOf<List<Product>>(listOf())
    var isLoading = mutableStateOf(false)

    private var scrollPosition = 0

    init {
        getProductByCategoryId(
            categoryId, pageIndex.value, pageSize
        ) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun getProductByCategoryId(
        categoryId: Long,
        pageIndex: Int,
        pageSize: Int,
        onResponse: (ServiceResponse<Product>) -> Unit,
    ) {
        viewModelScope.launch {
            var response = productRepository.getProductByCategoryId(categoryId, pageIndex, pageSize)
            onResponse(response)
        }
    }

    fun incrementPage() {
        pageIndex.value = pageIndex.value + 1

    }

    fun onScrollPositionChange(position: Int) {
        scrollPosition = position

    }

    fun appendItem(items: List<Product>) {
        var current = ArrayList(dataList.value)
        current.addAll(items)
        dataList.value = current

    }

    fun nextPage() {
        viewModelScope.launch {
            if ((scrollPosition + 1) >= (pageIndex.value * pageSize)) {
                isLoading.value = true
                incrementPage()
                if (pageIndex.value > 0) {
                    getProductByCategoryId(categoryId, pageIndex.value, pageSize) { response ->
                        if (response.status == "OK" && response.data!!.isNotEmpty())
                            appendItem(response.data)
                    }
                    isLoading.value = false
                }

            }

        }
    }
}