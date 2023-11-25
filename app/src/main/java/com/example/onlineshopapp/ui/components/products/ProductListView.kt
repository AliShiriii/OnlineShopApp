package com.example.onlineshopapp.ui.components.products

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductListView() {
    ProductFilterView()
    Spacer(modifier = Modifier.padding(20.dp))
    LazyRow{
        items(1){index->
            ProductListItemView()
        }
    }
}