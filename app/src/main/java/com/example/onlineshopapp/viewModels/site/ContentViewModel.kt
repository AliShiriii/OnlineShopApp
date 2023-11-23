package com.example.onlineshopapp.viewModels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Content
import com.example.onlineshopapp.repositoris.site.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(private val contentRepository: ContentRepository) :
    ViewModel() {

    fun getContent(onResponse: (response: ServiceResponse<Content>) -> Unit) {
        viewModelScope.launch {
            var response = contentRepository.getContent()

            onResponse(response)
        }
    }

    fun getContentByTitle(title: String, onResponse: (response: ServiceResponse<Content>) -> Unit) {
        viewModelScope.launch {
            var response = contentRepository.getContentByTitle(title)

            onResponse(response)
        }
    }

}