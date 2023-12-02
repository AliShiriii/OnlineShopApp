package com.example.onlineshopapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshopapp.R
import com.example.onlineshopapp.db.models.BasketEntity
import com.example.onlineshopapp.db.viewModels.BasketEntityViewModel
import com.example.onlineshopapp.ui.components.BasketItemView
import com.example.onlineshopapp.ui.theme.Dark
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun BasketListScreen(navController: NavHostController, basketViewModel: BasketEntityViewModel) {

    var dataList by remember { mutableStateOf(basketViewModel.dataList) }
    Column(Modifier.padding(20.dp, 0.dp)) {
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
        Spacer(modifier = Modifier.height(10.dp))
        if (dataList.value.isEmpty()) {

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
        LazyColumn(Modifier.padding(20.dp)) {
            items(basketViewModel.dataList.value.size) { index ->
                BasketItemView(dataList.value[index], basketViewModel, navController)
            }
        }
        if (dataList.value.isNotEmpty()) {
            Spacer(modifier = Modifier.height(30.dp))
            Column(Modifier.padding(20.dp)) {
                Button(
                    onClick = {

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
        }
    }
}