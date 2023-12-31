package com.example.onlineshopapp.ui.components.slider

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlineshopapp.ui.components.LoadingInRow
import com.example.onlineshopapp.viewModels.site.SliderViewModel

@Composable
fun SliderListView(viewModel: SliderViewModel = hiltViewModel()) {

    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    if (isLoading.value) {
        LoadingInRow(modifier = Modifier.width(300.dp).height(200.dp), 2)
    } else {
        LazyRow {
            items(dataList.value.size) { index ->

                SliderItemView(dataList.value[index])
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}