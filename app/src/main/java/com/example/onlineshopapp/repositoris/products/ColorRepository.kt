package com.example.onlineshopapp.repositoris.products

import com.example.onlineshopapp.api.products.ColorApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ColorRepository @Inject constructor(private val colorApi: ColorApi) {
}