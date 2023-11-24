package com.example.onlineshopapp.ui.scrreens

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlineshopapp.ui.components.TopAppView

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppView()
    }) {

        NavHost(
            navController = navController,
            startDestination = "Home"
        ) {
            composable("Home") {
                fullScreen = false
                HomeScreen(navController)
            }
        }
    }
}