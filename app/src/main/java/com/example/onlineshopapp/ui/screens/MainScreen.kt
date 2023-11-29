package com.example.onlineshopapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.onlineshopapp.ui.components.TopAppView
import com.example.onlineshopapp.utils.ThisApp

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
            composable("products/{categoryId}/{title}",
                arguments = listOf(
                    navArgument("categoryId") { type = NavType.LongType },
                    navArgument("title") { type = NavType.StringType }
                )
            ) { backStack ->
                fullScreen = false
                val id = backStack.arguments?.getLong("categoryId")
                val title = backStack.arguments?.getString("title")
                ThisApp.productCategoryId = id!!
                ProductsScreen(id, title!!, navController)
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