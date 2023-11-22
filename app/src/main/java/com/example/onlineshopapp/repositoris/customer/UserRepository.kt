package com.example.onlineshopapp.repositoris.customer

import com.example.onlineshopapp.api.customer.UserApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(private val userApi: UserApi) {
}