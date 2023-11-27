package com.example.onlineshopapp.ui.scrreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.ui.components.Loading
import com.example.onlineshopapp.ui.components.products.ProductCategoryListView
import com.example.onlineshopapp.ui.components.products.ProductFilterView
import com.example.onlineshopapp.ui.components.products.ProductListItemView
import com.example.onlineshopapp.ui.components.slider.SliderListView
import com.example.onlineshopapp.viewModels.products.ProductViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: ProductViewModel = hiltViewModel(),
) {


    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    /*
    * 1) Slider
    * 2) Product Category
    * 3) (New, Popular) - Filter
    * 4) Products, (New, Popular)
    */

    LazyColumn(modifier = Modifier.padding(20.dp, 0.dp)) {
        item {
            SliderListView()
            Spacer(modifier = Modifier.padding(20.dp))
        }
        item {

            ProductCategoryListView()
            Spacer(modifier = Modifier.padding(20.dp))

        }

        item {
            ProductFilterView()
            Spacer(modifier = Modifier.padding(20.dp))
        }

        if (isLoading.value) {
            item {
                Loading(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(160.dp)
                )
            }
        } else

            items(dataList.value.size) { index ->
                ProductListItemView(dataList.value[index], navController)
                Spacer(modifier = Modifier.padding(10.dp))
            }
    }
}