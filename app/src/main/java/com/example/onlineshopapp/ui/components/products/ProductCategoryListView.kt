package com.example.onlineshopapp.ui.components.products

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlineshopapp.ui.components.Loading
import com.example.onlineshopapp.viewModels.products.ProductCategoryViewModel

@Composable
fun ProductCategoryListView(viewModel: ProductCategoryViewModel = hiltViewModel()) {

    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    if (isLoading.value) {
        Loading(160.dp, 3)
    } else {
        LazyRow {
            items(dataList.value.size) { index ->

                ProductCategoryItemView(dataList.value[index])
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}