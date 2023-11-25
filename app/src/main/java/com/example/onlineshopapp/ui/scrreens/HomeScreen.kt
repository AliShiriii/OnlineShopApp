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
    }
}

@Composable
fun SliderListView(viewModel: SliderViewModel = hiltViewModel()) {

    var dataList by remember { mutableStateOf(viewModel.dataList) }

    LazyRow {
        items(dataList.value.size) { index ->

            SliderItemView(dataList.value[index])
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SliderItemView(slider: Slider) {

    Card(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true
            ),
        shape = RoundedCornerShape(20.dp),
        onClick = {}
    ) {

        Box(modifier = Modifier) {

            GlideImage(
                imageModel = slider.image!!,
                loading = {
                    CircularProgressIndicator()

                },
                failure = {
                    Text(text = "image request failed.")
                })
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(
                        text = slider.title!!,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = slider.subTitle!!, color = Color.LightGray, fontSize = 20.sp)
                }
            }
        }
    }
}
