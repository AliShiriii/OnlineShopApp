package com.example.onlineshopapp.viewModels.invoices

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoices.Invoice
import com.example.onlineshopapp.models.products.Product
import com.example.onlineshopapp.repositoris.invoices.InvoiceRepository
import com.example.onlineshopapp.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(private val invoiceRepository: InvoiceRepository) :
    ViewModel() {

    var token: String = ThisApp.token
    var userId: Long = ThisApp.userId
    var pageSize = 3
    var pageIndex = mutableStateOf(0)
    private var scrollPosition = 0

    var dataList = mutableStateOf<List<Invoice>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getInvoiceByUserId(userId, pageIndex.value, pageSize) { response ->
            isLoading.value = false
            if (response.status == "OK") {

                dataList.value = response.data!!
            }
        }
    }

    fun addInvoice(
        invoice: Invoice,
        onResponse: (ServiceResponse<Invoice>) -> Unit,
    ) {
        viewModelScope.launch {
            var response = invoiceRepository.addInvoice(invoice, "")
            onResponse(response)
        }
    }

    fun getInvoiceById(
        id: Long,
        onResponse: (ServiceResponse<Invoice>) -> Unit,
    ) {
        viewModelScope.launch {
            var response = invoiceRepository.getInvoiceById(id, token)
            onResponse(response)
        }
    }

    fun getInvoiceByUserId(
        id: Long,
        pageIndex: Int,
        pageSize: Int,
        onResponse: (ServiceResponse<Invoice>) -> Unit,
    ) {
        viewModelScope.launch {
            var response = invoiceRepository.getInvoiceByUserId(id, pageIndex, pageSize, token)
            onResponse(response)
        }
    }

    fun incrementPage() {
        pageIndex.value = pageIndex.value + 1

    }

    fun onScrollPositionChange(position: Int) {
        scrollPosition = position

    }

    fun appendItem(items: List<Invoice>) {
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
                    getInvoiceByUserId(userId, pageIndex.value, pageSize) { response ->
                        if (response.status == "OK" && response.data!!.isNotEmpty())
                            appendItem(response.data)
                    }
                    isLoading.value = false
                }

            }

        }
    }
}