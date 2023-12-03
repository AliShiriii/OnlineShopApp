package com.example.onlineshopapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.onlineshopapp.MainActivity
import com.example.onlineshopapp.db.viewModels.BasketEntityViewModel
import com.example.onlineshopapp.ui.components.TopAppView
import com.example.onlineshopapp.utils.ThisApp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(mainActivity: MainActivity) {

    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }
    val basketViewModel =
        ViewModelProvider(mainActivity)[BasketEntityViewModel::class.java]

    basketViewModel.getAllBasketLive().observe(mainActivity) {
        basketViewModel.dataList.value = it
    }

    Scaffold(topBar = {
        if (!fullScreen)
            TopAppView(navController, basketViewModel)
    }) {

        NavHost(
            navController = navController,
            startDestination = "Home"
        ) {
            composable("Home") {
                fullScreen = false
                HomeScreen(navController)
            }
            composable("Basket") {
                fullScreen = true
                BasketListScreen(navController, basketViewModel)
            }
            composable("proceedToPayment") {
                fullScreen = true
                UserPaymentScreen(navController, basketViewModel, mainActivity)
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
                backStack.arguments?.getLong("product").let { productId ->
                    ShowProductsScreen(productId!!, navController, basketViewModel)
                }
            }
        }
    }
}