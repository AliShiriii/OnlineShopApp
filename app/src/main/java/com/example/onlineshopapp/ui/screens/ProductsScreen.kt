package com.example.onlineshopapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.ui.components.LoadingInColumn
import com.example.onlineshopapp.ui.components.products.ProductListItemView
import com.example.onlineshopapp.viewModels.products.ProductByCategoryViewModel

@Composable
fun ProductsScreen(
    categoryId: Long,
    title: String,
    navController: NavHostController,
    viewModel: ProductByCategoryViewModel = hiltViewModel(),
) {

    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }
    var animatedVisibilityState =
        remember { MutableTransitionState(false) }.apply { targetState = true }

    LazyColumn(Modifier.padding(20.dp, 0.dp)) {
        item {
            AnimatedVisibility(
                visibleState = animatedVisibilityState,
                enter = slideInVertically(
                    animationSpec = tween(500, 500),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500, 500),

                    ),
                exit = fadeOut()
            ) {
                Text(text = title, fontSize = 26.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(dataList.value.size) { index ->
            viewModel.onScrollPositionChange(index)

            if ((index + 1) >= (viewModel.pageIndex.value * viewModel.pageSize) && !viewModel.isLoading.value) {
                viewModel.nextPage()
            }
            AnimatedVisibility(
                visibleState = animatedVisibilityState,
                enter = slideInVertically(
                    animationSpec = tween(500, 1000),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500, 1000),

                    ),
                exit = fadeOut()
            ) {
                ProductListItemView(dataList.value[index], navController)
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }
        if (isLoading.value) {
            item {
                LoadingInColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }
}