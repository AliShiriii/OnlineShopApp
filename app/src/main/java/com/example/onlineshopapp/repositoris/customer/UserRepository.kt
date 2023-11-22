package com.example.onlineshopapp.repositoris.customer

import com.example.onlineshopapp.api.customer.UserApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.customer.User
import com.example.onlineshopapp.models.customer.UserVM
import com.example.onlineshopapp.repositoris.base.BaseRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(private val userApi: UserApi): BaseRepository() {

    suspend fun getUserInfo(token: String): ServiceResponse<User> {

        return try {
            userApi.getUserInfo(prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getBlogById(
        user: UserVM,
        token: String,
    ): ServiceResponse<User> {

        return try {
            userApi.changePassword(user, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun login(
        user: UserVM,
    ): ServiceResponse<UserVM> {

        return try {
            userApi.login(user)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun register(
        user: UserVM,
    ): ServiceResponse<User> {

        return try {
            userApi.register(user)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun update(
        user: UserVM,
        token: String,
    ): ServiceResponse<User> {

        return try {
            userApi.update(user, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}