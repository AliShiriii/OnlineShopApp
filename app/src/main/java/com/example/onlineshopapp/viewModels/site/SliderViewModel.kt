package com.example.onlineshopapp.viewModels.site

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    var dataList = mutableStateOf<List<Slider>>(listOf())

    init {

        getSliders { response ->
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

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