package com.example.onlineshopapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshopapp.db.viewModels.UserEntityViewModel
import com.example.onlineshopapp.ui.screens.SplashScreen
import com.example.onlineshopapp.ui.ui.theme.OnlineShopAppTheme
import com.example.onlineshopapp.utils.ThisApp
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isLoad by remember { mutableStateOf(false) }
            val userEntityViewModel =
                ViewModelProvider(this)[UserEntityViewModel::class.java]

            userEntityViewModel.getCurrentUser().observe(this) {
                userEntityViewModel.currentUser.value = it
                isLoad = true
            }
            OnlineShopAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (userEntityViewModel.currentUser.value!!.token != null || isLoad) {
                        ThisApp.token = userEntityViewModel.currentUser.value!!.token!!
                    }
                    if (isLoad)
                        SplashScreen(this, userEntityViewModel)
                }
            }
        }
    }
}