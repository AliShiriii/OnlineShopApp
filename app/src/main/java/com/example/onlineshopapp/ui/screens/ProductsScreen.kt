package com.example.onlineshopapp.ui.screens

import android.widget.Space
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.models.products.Product
import com.example.onlineshopapp.ui.components.Loading
import com.example.onlineshopapp.ui.components.products.ProductListItemView
import com.example.onlineshopapp.viewModels.products.ProductViewModel

@Composable
fun ProductsScreen(
    categoryId: Long,
    title: String,
    navController: NavHostController,
    viewModel: ProductViewModel = hiltViewModel(),
) {

    var dataList by remember { mutableStateOf<List<Product>>(listOf()) }
    var isLoading by remember { mutableStateOf(true) }

    viewModel.getProductByCategoryId(categoryId, 0, 10) { response ->
        isLoading = false
        if (response.status == "OK") {
            dataList = response.data!!
        }
    }

    if (isLoading) {
        Loading(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), 6
        )
    } else {
        LazyColumn(Modifier.padding(20.dp)) {
            item {
                Text(text = title, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(dataList.size) { index ->
                ProductListItemView(dataList[index], navController)
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}