package com.example.onlineshopapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.ui.components.AdvancedButton
import com.example.onlineshopapp.ui.components.LoadingInColumn
import com.example.onlineshopapp.ui.theme.LightBlue
import com.example.onlineshopapp.ui.theme.Red
import com.example.onlineshopapp.viewModels.invoices.InvoiceItemViewModel

@Composable
fun InvoiceScreen(
    navController: NavHostController,
    string: Long?,
    viewModel: InvoiceItemViewModel = hiltViewModel(),
) {

    var invoice by remember { mutableStateOf(viewModel.data) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    if (isLoading.value || invoice.value == null) {

        LoadingInColumn(
            modifier = Modifier
                .fillMaxSize()
        )
    } else {
        Column {
            Row {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
                Spacer(modifier = Modifier.width(5.dp))
                Column {
                    Text(
                        text = "Invoice", fontSize = 20.sp, textAlign = TextAlign.Center,
                        modifier = Modifier.padding(0.dp, 9.dp, 0.dp, 0.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(25.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    modifier = Modifier.padding(5.dp),
                    shape = RoundedCornerShape(50.dp),
                    backgroundColor = if (invoice.value!!.status == "NotPayed") Red else Color.Green

                ) {
                    Icon(
                        imageVector = if (invoice.value!!.status == "NotPayed") Icons.Filled.Close else Icons.Filled.Check,
                        contentDescription = "",
                        Modifier
                            .size(200.dp)
                            .padding(30.dp),
                        tint = Color.White
                    )
                }
            }

            Column(Modifier.padding(20.dp)) {

                Spacer(modifier = Modifier.height(25.dp))
                Text(text = "Add  Date: ${invoice.value!!.addDate}", fontSize = 21.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Add  Date: ${invoice.value!!.addDate}", fontSize = 21.sp)
                Spacer(modifier = Modifier.height(5.dp))
                if (!invoice.value!!.paymentData.isNullOrEmpty()) {
                    Text(text = "Payment  Date: ${invoice.value!!.paymentData}", fontSize = 21.sp)
                    Spacer(modifier = Modifier.height(15.dp))
                }

                LazyColumn(modifier = Modifier.fillMaxWidth()) {

                    items(invoice.value!!.items.size) { index ->

                        AdvancedButton(
                            invoice.value!!.items[index].product!!.title!!,
                            invoice.value!!.items[index].quantity!!.toString(),
                            Icons.Filled.List,
                            LightBlue
                        ) {
                        }
                    }
                }

            }
        }
    }
}