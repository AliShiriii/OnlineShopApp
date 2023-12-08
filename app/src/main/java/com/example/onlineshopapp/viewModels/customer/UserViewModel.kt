package com.example.onlineshopapp.viewModels.customer

import android.view.ViewManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.customer.User
import com.example.onlineshopapp.models.customer.UserVM
import com.example.onlineshopapp.models.products.ProductColor
import com.example.onlineshopapp.repositoris.customer.UserRepository
import com.example.onlineshopapp.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    var token: String = ThisApp.token

    fun getUserInfo(onResponse: (ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            var response = userRepository.getUserInfo("")
            onResponse(response)
        }
    }

    fun changePassword(data: UserVM, onResponse: (ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            var response = userRepository.changePassword(data, token)
            onResponse(response)
        }
    }

    fun login(data: UserVM, onResponse: (ServiceResponse<UserVM>) -> Unit) {
        viewModelScope.launch {
            var response = userRepository.login(data)
            onResponse(response)
        }
    }

    fun register(data: UserVM, onResponse: (ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            var response = userRepository.register(data)
            onResponse(response)
        }
    }

    fun update(data: UserVM, onResponse: (ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            var response = userRepository.update(data, token)
            onResponse(response)
        }
    }


}