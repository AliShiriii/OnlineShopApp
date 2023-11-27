package com.example.onlineshopapp.ui.scrreens

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.onlineshopapp.models.products.Product
import com.example.onlineshopapp.ui.components.TopAppView
import com.google.gson.Gson

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        if (!fullScreen)
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

            composable(
                "showProduct/{product}",
                arguments = listOf(navArgument("product") { type = NavType.LongType })
            ) { backStack ->
                fullScreen = true
                backStack.arguments?.getLong("product").let {
                    ShowProductsScreen(it!!, navController)
                }
            }
        }
    }
}