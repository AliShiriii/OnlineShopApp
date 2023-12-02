package com.example.onlineshopapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlineshopapp.db.models.BasketEntity
import com.example.onlineshopapp.db.viewModels.BasketEntityViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasketItemView(
    basketEntity: BasketEntity,
    basketViewModel: BasketEntityViewModel,
    totalPrice: MutableState<Long>,
    navController: NavController,
) {

    var quantity by remember { mutableStateOf(basketEntity.quantity) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp)
    ) {
        Card(
            modifier = Modifier
                .size(100.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp), clip = true),
            shape = RoundedCornerShape(20.dp),
            onClick = { navController.navigate("showProduct/${basketEntity.productId}") }
        ) {
            GlideImage(
                imageModel = basketEntity.image!!,
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
        Spacer(modifier = Modifier.width(20.dp))

        Column {
            Text(text = basketEntity.title!!, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "${basketEntity.price!!}",
                fontWeight = FontWeight.Light,
                color = Color.Gray,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(5.dp))

            Row {
                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            basketViewModel.decrementQuantity(basketEntity)
                        }
                        quantity--
                        totalPrice.value -= basketEntity.price
                    },
                    Modifier.size(26.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = quantity.toString(), fontSize = 20.sp)
                Spacer(modifier = Modifier.width(10.dp))

                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            basketViewModel.incrementQuantity(basketEntity)
                        }

                        quantity++
                    },
                    modifier = Modifier.size(26.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            basketViewModel.delete(basketEntity)
                        }
                    },
                    modifier = Modifier.size(25.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "",
                        tint = Color.Red
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(5.dp))

        Card(
            shape = RoundedCornerShape(15.dp),
            backgroundColor =
            Color(android.graphics.Color.parseColor("#${basketEntity.colorHex}")),
            modifier = Modifier.size(40.dp),
            border = BorderStroke(1.dp, Color.White),
            content = {}
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = basketEntity.size, fontSize = 22.sp, color = Color.Gray)
    }
    Spacer(modifier = Modifier.height(10.dp))
}