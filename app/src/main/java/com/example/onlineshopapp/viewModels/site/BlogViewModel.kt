package com.example.onlineshopapp.viewModels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Blog
import com.example.onlineshopapp.models.site.Content
import com.example.onlineshopapp.repositoris.site.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(private val blogRepository: BlogRepository) : ViewModel() {


    fun getBlog(onResponse: (response: ServiceResponse<Blog>) -> Unit) {
        viewModelScope.launch {
            var response = blogRepository.getBlog()

            onResponse(response)
        }
    }

    fun getBlogById(id: Long, onResponse: (response: ServiceResponse<Blog>) -> Unit) {
        viewModelScope.launch {
            var response = blogRepository.getBlogById(id)

            onResponse(response)
        }
    }

}