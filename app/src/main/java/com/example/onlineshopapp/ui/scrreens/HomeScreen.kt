package com.example.onlineshopapp.ui.scrreens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.models.site.Slider
import com.example.onlineshopapp.ui.components.products.ProductCategoryListView
import com.example.onlineshopapp.ui.components.products.ProductListView
import com.example.onlineshopapp.ui.components.slider.SliderItemView
import com.example.onlineshopapp.ui.components.slider.SliderListView
import com.example.onlineshopapp.viewModels.site.SliderViewModel
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(navController: NavHostController) {

    /*
    * 1) Slider
    * 2) Product Category
    * 3) (New, Popular) - Filter
    * 4) Products, (New, Popular)
    */

    Column(modifier = Modifier.padding(20.dp)) {
        SliderListView()
        Spacer(modifier = Modifier.padding(20.dp))
        ProductCategoryListView()
        Spacer(modifier = Modifier.padding(20.dp))
        ProductListView()
    }
}