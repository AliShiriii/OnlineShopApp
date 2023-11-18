package com.example.onlineshopapp.Modules

import com.example.onlineshopapp.config.UnsafeSSLConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModules {

    @Singleton
    @Provides
    fun provideApi(): Retrofit {

        return Retrofit.Builder().baseUrl("https://10.0.2.2:8080/")
            .client(UnsafeSSLConfig.getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}