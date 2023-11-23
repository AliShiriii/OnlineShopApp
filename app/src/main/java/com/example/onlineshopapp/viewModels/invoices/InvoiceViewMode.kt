package com.example.onlineshopapp.viewModels.invoices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoices.Invoice
import com.example.onlineshopapp.repositoris.invoices.InvoiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(private val invoiceRepository: InvoiceRepository) :
    ViewModel() {

    fun addInvoice(invoice: Invoice, onResponse: (ServiceResponse<Invoice>) -> Unit) {
        viewModelScope.launch {
            var response = invoiceRepository.addInvoice(invoice, "")
            onResponse(response)
        }
    }

    fun getProductById(id: Long, onResponse: (ServiceResponse<Invoice>) -> Unit) {
        viewModelScope.launch {
            var response = invoiceRepository.getInvoiceById(id, "")
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
            var response = invoiceRepository.getInvoiceByUserId(id, pageIndex, pageSize, "")
            onResponse(response)
        }
    }

}