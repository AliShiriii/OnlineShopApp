package com.example.onlineshopapp.viewModels.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Slider
import com.example.onlineshopapp.repositoris.site.SliderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(private val sliderRepository: SliderRepository) :
    ViewModel() {

    fun getSliders(onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            var response = sliderRepository.getSlider()

            onResponse(response)
        }

    }

    fun getSliderById(id: Long, onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            var response = sliderRepository.getSliderById(id)

            onResponse(response)
        }

    }

}