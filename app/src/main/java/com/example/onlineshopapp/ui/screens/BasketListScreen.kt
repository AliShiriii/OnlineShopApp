package com.example.onlineshopapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewModels.BasketEntityViewModel
import com.example.onlineshopapp.ui.components.BasketItemView
import com.example.onlineshopapp.ui.theme.Dark

@Composable
fun BasketListScreen(navController: NavHostController, basketViewModel: BasketEntityViewModel) {

    var dataList by remember { mutableStateOf(basketViewModel.dataList) }
    var totalPriceLong: Long = 0

    for (item in dataList.value) {
        totalPriceLong += item.quantity * item.price!!
    }
    var totalPrice = remember { mutableStateOf<Long>(totalPriceLong) }

    LazyColumn {
        item {
            Row {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
                Spacer(modifier = Modifier.width(5.dp))
                Column {
                    Text(text = "Sopping Cart", fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(5.dp))
                    if (dataList.value.isNotEmpty()) {
                        Text(
                            text = "${basketViewModel.dataList.value.size} Items",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
        if (dataList.value.isEmpty()) {
            item {
                Column(
                    Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Your shopping cart is empty!", fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(40.dp))
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "",
                        Modifier.size(250.dp), tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }

        items(basketViewModel.dataList.value.size) { index ->
            BasketItemView(dataList.value[index], basketViewModel, totalPrice, navController)
        }

        item {
            if (dataList.value.isNotEmpty()) {
                Spacer(modifier = Modifier.height(15.dp))
                Row {
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Total Price : ", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = "${totalPrice.value}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Column(Modifier.padding(20.dp)) {
                    Button(
                        onClick = {
                            navController.navigate("proceedToPayment")
                        },
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Dark
                        ),
                    ) {
                        Text(
                            text = "Proceed To Payment",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))

//                Button(
//                    onClick = {
//                    },
//                    shape = RoundedCornerShape(15.dp),
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .height(50.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        backgroundColor = Dark
//                    ),
//                ) {
//                    Text(
//                        text = "\$Pay",
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    )
//                }
//                Spacer(modifier = Modifier.width(30.dp))
//
            }
        }
    }
}