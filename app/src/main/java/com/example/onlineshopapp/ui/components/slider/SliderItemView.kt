package com.example.onlineshopapp.ui.components.slider

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlineshopapp.models.site.Slider
import com.skydoves.landscapist.glide.GlideImage

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
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                loading = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
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