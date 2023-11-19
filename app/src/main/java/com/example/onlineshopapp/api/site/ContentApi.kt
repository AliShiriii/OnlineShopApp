package com.example.onlineshopapp.api.site

import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Blog
import com.example.onlineshopapp.models.site.Content
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentApi {

    @GET("/api/blog")
    suspend fun getContent(): ServiceResponse<Content>

    @GET("/api/blog/{id}")
    suspend fun getContentById(@Path("id") id: Long): ServiceResponse<Content>

}