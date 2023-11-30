package com.example.onlineshopapp.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TopAppView(navController: NavHostController) {

    TopAppBar(
        title = { Text(text = "Online Shoe", fontSize = 2.sp) },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { navController.navigate("basket") }) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "")
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.Person, contentDescription = "")
            }
        })
}