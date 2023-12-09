package com.example.onlineshopapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.models.BasketEntity
import com.example.onlineshopapp.db.viewModels.BasketEntityViewModel
import com.example.onlineshopapp.ui.components.LoadingInColumn
import com.example.onlineshopapp.viewModels.products.ProductViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun ShowProductsScreen(
    productId: Long,
    navController: NavHostController,
    basketVieModel: BasketEntityViewModel,
    viewModel: ProductViewModel = hiltViewModel(),
) {

    var data by remember { mutableStateOf(viewModel.data) }
    var isLoading by remember { mutableStateOf(true) }
    var selectedSize by remember { mutableStateOf(0) }
    var selectedColors by remember { mutableStateOf(0) }
    val context = LocalContext.current
    viewModel.getProductById(productId) { response ->
        isLoading = false
        if (response.status == "Ok") {
            if (response.data!!.isNotEmpty())
                viewModel.data.value = response.data[0]
        } else {
            Toast.makeText(context, "Error on load data!", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
    }

    if (isLoading) {
        LoadingInColumn(modifier = Modifier.fillMaxSize())
    } else {

        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(0.dp)
        ) {

            Box(modifier = Modifier) {

                GlideImage(
                    imageModel = data.value?.image!!,
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
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {

                IconButton(onClick = { navController.popBackStack() }) {

                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "", tint = Color.White
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Text(
                        text = data.value?.title!!,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "${data.value?.price!!}T",
                        color = Color.LightGray,
                        fontSize = 26.sp
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "Sizes",
                        color = Color.LightGray,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.padding(15.dp))

                    LazyRow {
                        items(data.value?.sizes!!.size) { index ->

                            TextButton(
                                onClick = { selectedSize = index },
                                shape = RoundedCornerShape(15.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor =
                                    if (selectedSize == index) Color.White else Color.Transparent
                                ),
                                modifier = Modifier.size(40.dp)
                            ) {

                                Text(
                                    text = data.value?.sizes!![index].title!!,
                                    fontWeight = FontWeight.Bold,
                                    color = if (selectedSize == index) Color.White else Color.Black
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }

                    Spacer(modifier = Modifier.padding(15.dp))
                    Text(
                        text = "Colors",
                        color = Color.LightGray,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow {
                        items(data.value?.colors!!.size) { index ->

                            TextButton(
                                onClick = { selectedColors = index },
                                shape = RoundedCornerShape(15.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor =
                                    Color(android.graphics.Color.parseColor("#${data.value?.colors!![index].hexValue}"))
                                ),
                                modifier = Modifier.size(40.dp),
                                border = BorderStroke(1.dp, Color.White)
                            ) {
                                if (selectedColors == index) {
                                    Icon(
                                        imageVector = Icons.Filled.Check, contentDescription = "",
                                        tint = if (data.value?.colors!![index].hexValue?.lowercase(Locale.ROOT)
                                            == "fffffff") Color.Black else Color.White
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                    Spacer(modifier = Modifier.padding(40.dp))

                    Button(
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                val basket = BasketEntity(
                                    productId = productId,
                                    quantity = 1,
                                    sizeId = data.value!!.sizes!![selectedSize].id!!,
                                    colorId = data.value!!.colors!![selectedColors].id!!,
                                    image = data.value!!.image,
                                    price = data.value!!.price,
                                    title = data.value!!.title,
                                    colorHex = data.value!!.colors?.get(selectedColors)?.hexValue!!,
                                    size = data.value!!.sizes?.get(selectedSize)?.title!!
                                )
                                basketVieModel.addTooBasket(basket)
                            }
                            Toast.makeText(
                                context,
                                "Product added to your basket!",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.popBackStack()
                        },
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor =  Color.White
                        ),
                    ) {
                        Text(text = "Buy Now", fontWeight = FontWeight.Bold, color = Color.Black)
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                }
            }
        }
    }
}