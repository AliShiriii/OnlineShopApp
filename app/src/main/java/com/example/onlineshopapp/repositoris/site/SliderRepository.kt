package com.example.onlineshopapp.repositoris.site

import com.example.onlineshopapp.api.site.SliderApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Slider
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SliderRepository @Inject constructor(private val sliderApi: SliderApi) {

    suspend fun getSlider(): ServiceResponse<Slider> {

        return try {
            sliderApi.getSlider()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getSliderById(id: Long): ServiceResponse<Slider> {

        return try {
            sliderApi.getSliderById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

}