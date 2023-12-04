@file:Suppress("UNUSED_EXPRESSION")

package com.example.onlineshopapp.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopapp.MainActivity
import com.example.onlineshopapp.db.viewModels.BasketEntityViewModel
import com.example.onlineshopapp.models.customer.Customer
import com.example.onlineshopapp.models.customer.User
import com.example.onlineshopapp.models.customer.UserVM
import com.example.onlineshopapp.models.invoices.InvoiceItem
import com.example.onlineshopapp.models.invoices.PaymentTransaction
import com.example.onlineshopapp.ui.theme.Dark
import com.example.onlineshopapp.viewModels.invoices.TransactionViewModel

@Composable
fun UserPaymentScreen(
    navController: NavController,
    basketEntityViewModel: BasketEntityViewModel,
    mainActivity: MainActivity,
    transactionViewModel: TransactionViewModel = hiltViewModel(),
) {

    var context = LocalContext.current

    var firstName by remember { mutableStateOf(TextFieldValue()) }
    var firstNameError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf(TextFieldValue()) }
    var lastNameError by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf(TextFieldValue()) }
    var phoneError by remember { mutableStateOf(false) }
    var postalCode by remember { mutableStateOf(TextFieldValue()) }
    var postalCodeError by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf(TextFieldValue()) }
    var addressError by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf(TextFieldValue()) }
    var userNameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    Column {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Complete your information",
                textAlign = TextAlign.Center,
                fontSize = 24.sp, modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
            )
        }
        LazyColumn {
            item {
                Column(Modifier.padding(20.dp)) {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = {
                            firstName = it
                            firstNameError
                        },
                        label = { Text(text = "First Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        trailingIcon = {
                            if (firstNameError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )

                            }
                        },
                        isError = firstNameError
                    )
                    if (firstNameError) {
                        Text(
                            text = "Please enter your first name",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = {
                            lastName = it
                            lastNameError
                        },
                        singleLine = true,
                        label = { Text(text = "Last Name") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            if (lastNameError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )

                            }
                        },
                        isError = lastNameError
                    )
                    if (lastNameError) {
                        Text(
                            text = "Please enter your last name",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                            phoneError
                        },
                        singleLine = true,
                        label = { Text(text = "Phone") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            if (phoneError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )

                            }
                        },
                        isError = phoneError
                    )
                    if (phoneError) {
                        Text(
                            text = "Please enter your phone",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = userName,
                        onValueChange = {
                            userName = it
                            userNameError
                        },
                        singleLine = true,
                        label = { Text(text = "User Name") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            if (userNameError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )

                            }
                        },
                        isError = userNameError
                    )
                    if (userNameError) {
                        Text(
                            text = "Please enter your user name",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError
                        },
                        singleLine = true,
                        label = { Text(text = "Password") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            if (passwordError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )

                            }
                        },
                        isError = passwordError
                    )
                    if (passwordError) {
                        Text(
                            text = "Please enter your password",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = address,
                        onValueChange = {
                            address = it
                            addressError
                        },
                        label = { Text(text = "Address") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            if (addressError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )

                            }
                        },
                        isError = addressError
                    )
                    if (addressError) {
                        Text(
                            text = "Please enter your address",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = postalCode,
                        onValueChange = {
                            postalCode = it
                            postalCodeError
                        },
                        singleLine = true,
                        label = { Text(text = "Postal Code") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            if (postalCodeError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )

                            }
                        },
                        isError = postalCodeError
                    )
                    if (postalCodeError) {
                        Text(
                            text = "Please enter your postal code",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    if (!isLoading) {
                        Button(
                            onClick = {
                                var hasError = false

                                if (firstName.text.isEmpty()) {
                                    firstNameError = true
                                }
                                if (lastName.text.isEmpty()) {
                                    lastNameError = true
                                }
                                if (phone.text.isEmpty()) {
                                    phoneError = true
                                }
                                if (userName.text.isEmpty()) {
                                    userNameError = true
                                }
                                if (password.text.isEmpty()) {
                                    passwordError = true
                                }
                                if (address.text.isEmpty()) {
                                    addressError = true
                                }
                                if (postalCode.text.isEmpty()) {
                                    postalCodeError = true
                                }
                                if (firstNameError || lastNameError || phoneError
                                    || userNameError || passwordError || addressError
                                    || postalCodeError
                                ) {
                                    return@Button
                                }
                                var userInfo = UserVM(
                                    username = userName.text,
                                    password = password.text,
                                    firstName = firstName.text,
                                    lastName = lastName.text,
                                    address = address.text,
                                    phone = phone.text,
                                    postalCode = postalCode.text
                                )
                                var items = ArrayList<InvoiceItem>()
                                basketEntityViewModel.dataList.value.forEach {
                                    items.add(InvoiceItem.convertFromBasket(it))
                                }
                                var request = PaymentTransaction(user = userInfo, items = items)

                                isLoading = true
                                transactionViewModel.goToPayment(request) { response ->
                                    if (response.status == "Ok" && response.data!!.isNotEmpty()) {
                                        val intentBrowser =
                                            Intent(Intent.ACTION_VIEW, Uri.parse(response.data[0]))
                                        context.startActivity(intentBrowser)
                                        mainActivity.finish()
                                    } else if (response.message!!.isNotEmpty()) {

                                        Toast.makeText(
                                            context,
                                            response.message,
                                            Toast.LENGTH_SHORT).show()
                                    }
                                    isLoading = false

                                }

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
                                text = "\$Pay",
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                    if (isLoading) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    Spacer(modifier = Modifier.width(30.dp))

                }
            }
        }
    }
}