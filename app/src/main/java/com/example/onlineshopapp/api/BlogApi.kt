package com.example.onlineshopapp.api

import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Blog
import com.example.onlineshopapp.models.site.Slider
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogApi {

    @GET("/api/slider")
    suspend fun getBlog(): ServiceResponse<Blog>

    @GET("/api/slider/{id}")
    suspend fun getBlogById(@Path("id") id: Long): ServiceResponse<Blog>


}