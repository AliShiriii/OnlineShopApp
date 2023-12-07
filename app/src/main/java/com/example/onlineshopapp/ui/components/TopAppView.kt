package com.example.onlineshopapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewModels.BasketEntityViewModel
import com.example.onlineshopapp.db.viewModels.UserEntityViewModel

@Composable
fun TopAppView(
    navController: NavHostController,
    basketViewModel: BasketEntityViewModel,
    userEntityViewModel: UserEntityViewModel,
    showHomeButton: Boolean = false,
) {

    TopAppBar(
        title = { Text(text = "Online Shoe", fontSize = 2.sp) },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            if (showHomeButton) {
                IconButton(onClick = { navController.navigate("home") }
                ) {
                    Icon(imageVector = Icons.Outlined.Home, contentDescription = "home")
                }
            }
            IconButton(onClick = { navController.navigate("basket") }) {
                Box(contentAlignment = Alignment.BottomEnd) {
                    Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "")
                    if (basketViewModel.dataList.value.isNotEmpty()) {
                        Card(
                            shape = RoundedCornerShape(50.dp),
                            backgroundColor = Color.Red,
                            elevation = 0.dp,
                            modifier = Modifier.size(14.dp)
                        ) {
                            Text(
                                text = "${basketViewModel.dataList.value.size}",
                                color = Color.White,
                                fontSize = 8.sp,
                                modifier = Modifier.padding(1.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            IconButton(onClick = {

                if (userEntityViewModel.isLoggedIn()) {
                    navController.navigate("login")
                } else {
                    navController.navigate("dashboard")
                }
            }

            ) {
                Icon(imageVector = Icons.Outlined.Person, contentDescription = "")
            }
        })
}