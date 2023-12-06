package com.example.onlineshopapp.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewModels.UserEntityViewModel

@Composable
fun DashboardScreen(navController: NavHostController, userEntityViewModel: UserEntityViewModel) {

    Text(text = "User Dashboard${userEntityViewModel.currentUser.value?.firstName}")

}