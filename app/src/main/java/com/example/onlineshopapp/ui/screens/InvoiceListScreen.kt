package com.example.onlineshopapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopapp.ui.components.invoices.InvoiceListItemView
import com.example.onlineshopapp.ui.components.LoadingInColumn
import com.example.onlineshopapp.viewModels.invoices.InvoiceViewModel

@Composable
fun InvoiceListScreen(navController: NavController, viewModel: InvoiceViewModel = hiltViewModel()) {

    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    Column {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Text(
                    text = "Invoices", fontSize = 20.sp, textAlign = TextAlign.Center,
                    modifier = Modifier.padding(0.dp, 9.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))

            }
        }

    }
    LazyColumn() {

        items(dataList.value.size) { index ->
            viewModel.onScrollPositionChange(index)

            if ((index + 1) >= (viewModel.pageIndex.value * viewModel.pageSize) && !viewModel.isLoading.value) {
                viewModel.nextPage()
            }
            InvoiceListItemView(dataList.value[index], navController)
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