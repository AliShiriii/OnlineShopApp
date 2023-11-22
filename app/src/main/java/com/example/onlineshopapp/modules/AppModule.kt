package com.example.onlineshopapp.modules

import com.example.onlineshopapp.api.site.SliderApi
import com.example.onlineshopapp.repositoris.site.SliderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSliderRepository(sliderApi: SliderApi) = SliderRepository(sliderApi)
}