package com.example.onlineshopapp.api.customer

import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.customer.User
import com.example.onlineshopapp.models.customer.UserVM
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface UserApi {

    @GET("/api/blog")
    suspend fun getUserInfo(@Header("Authorization") token: String): ServiceResponse<User>

    @PUT("/api/user/changePassword/")
    suspend fun getBlogById(
        @Body user: UserVM,
        @Header("Authorization") token: String,
    ): ServiceResponse<User>

    @GET("/api/user/login/")
    suspend fun login(
        @Body user: UserVM,
    ): ServiceResponse<UserVM>

    @GET("/api/user/login/")
    suspend fun register(
        @Body user: UserVM,
    ): ServiceResponse<User>

    @PUT("/api/user/update/")
    suspend fun update(
        @Body user: UserVM,
        @Header("Authorization") token: String,
    ): ServiceResponse<User>

}