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
import androidx.navigation.navDeepLink
import com.example.onlineshopapp.MainActivity
import com.example.onlineshopapp.db.viewModels.BasketEntityViewModel
import com.example.onlineshopapp.db.viewModels.UserEntityViewModel
import com.example.onlineshopapp.ui.components.TopAppView
import com.example.onlineshopapp.utils.ThisApp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(mainActivity: MainActivity) {

    val navController = rememberNavController()
    var fullScreen by remember { mutableStateOf(false) }
    var showHomeButton by remember { mutableStateOf(false) }

    val basketViewModel =
        ViewModelProvider(mainActivity)[BasketEntityViewModel::class.java]
    val userEntityViewModel =
        ViewModelProvider(mainActivity)[UserEntityViewModel::class.java]
    basketViewModel.getAllBasketLive().observe(mainActivity) {
        basketViewModel.dataList.value = it
    }
    userEntityViewModel.getCurrentUser().observe(mainActivity) {
        userEntityViewModel.currentUser.value = it
    }

    Scaffold(topBar = {
        if (!fullScreen)
            TopAppView(navController, basketViewModel, userEntityViewModel, showHomeButton)
    }) {

        NavHost(
            navController = navController,
            startDestination = "Home"
        ) {
            composable("Home") {
                fullScreen = false
                showHomeButton = false
                HomeScreen(navController)
            }
            composable("Basket") {
                fullScreen = true
                showHomeButton = false
                BasketListScreen(navController, basketViewModel)
            }
            composable("proceedToPayment") {
                fullScreen = true
                showHomeButton = false
                UserPaymentScreen(navController, basketViewModel, mainActivity)
            }
            composable("products/{categoryId}/{title}",
                arguments = listOf(
                    navArgument("categoryId") { type = NavType.LongType },
                    navArgument("title") { type = NavType.StringType }
                )
            ) { backStack ->
                fullScreen = false
                showHomeButton = false
                val id = backStack.arguments?.getLong("categoryId")
                val title = backStack.arguments?.getString("title")
                ThisApp.productCategoryId = id!!
                ProductsScreen(id, title!!, navController)
            }
            composable(
                "showProduct/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.LongType })
            ) { backStack ->
                fullScreen = true
                showHomeButton = false
                backStack.arguments?.getLong("productId").let { productId ->
                    ShowProductsScreen(productId!!, navController, basketViewModel)
                }
            }
            composable(
                "invoice/{id}",
                deepLinks = listOf(navDeepLink {
                    uriPattern = "app://onlineshopholosen.ir/{id}"
                }),
                arguments = listOf(navArgument("id") { type = NavType.LongType })

            ) { backStackEntry ->
                showHomeButton = true

                if (userEntityViewModel.currentUser.value != null) {
                    ThisApp.userId = backStackEntry.arguments?.getLong("id")!!
                    ThisApp.token = userEntityViewModel.currentUser.value!!.token!!
                }
                InvoiceScreen(navController, backStackEntry.arguments?.getLong("id"))
            }

            composable("login") {
                fullScreen = true
                showHomeButton = false
                LoginScreen(navController, userEntityViewModel)
            }

            composable("dashboard") {
                fullScreen = true
                showHomeButton = false
                DashboardScreen(navController, userEntityViewModel)
            }

            composable("invoices") {
                fullScreen = true
                showHomeButton = false
                InvoiceListScreen(navController)
            }
        }
    }
}