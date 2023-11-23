package com.example.onlineshopapp.viewModels.invoices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoices.Invoice
import com.example.onlineshopapp.models.invoices.PaymentTransaction
import com.example.onlineshopapp.repositoris.invoices.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val transactionRepository: TransactionRepository) :
    ViewModel() {

    fun goToPayment(data: PaymentTransaction, onResponse: (ServiceResponse<String>) -> Unit) {
        viewModelScope.launch {
            var response = transactionRepository.goToPayment(data)
            onResponse(response)
        }
    }

}