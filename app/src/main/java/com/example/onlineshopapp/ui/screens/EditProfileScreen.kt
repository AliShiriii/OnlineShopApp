package com.example.onlineshopapp.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewModels.UserEntityViewModel
import com.example.onlineshopapp.models.customer.UserVM
import com.example.onlineshopapp.models.invoices.InvoiceItem
import com.example.onlineshopapp.models.invoices.PaymentTransaction
import com.example.onlineshopapp.ui.theme.Dark
import com.example.onlineshopapp.utils.ThisApp
import com.example.onlineshopapp.viewModels.customer.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel(),
) {

    var context = LocalContext.current
    var currentUser by remember { mutableStateOf(userEntityViewModel.currentUser) }
    var isLoggedIn by remember { mutableStateOf(userEntityViewModel.currentUser.value != null) }

    var firstName by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.firstName!! else "")) }
    var firstNameError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.lastName!! else "")) }
    var lastNameError by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.phone!! else "")) }
    var phoneError by remember { mutableStateOf(false) }
    var postalCode by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.postalCode!! else "")) }
    var postalCodeError by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.address!! else "")) }
    var addressError by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf(TextFieldValue(if (isLoggedIn) currentUser.value!!.username!! else "")) }
    var userNameError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }
    var focusManager = LocalFocusManager.current


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
                            firstNameError = false
                        },
                        label = { Text(text = "First Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
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
                            lastNameError = false
                        },
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
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
                            phoneError = false
                        },
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Next
                        ),
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
                        enabled = currentUser.value == null,
                        onValueChange = {
                            userName = it
                            userNameError = false
                        },
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
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
                            text = "Please enter your username",
                            color = Color.Red,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = postalCode,
                        onValueChange = {
                            postalCode = it
                            postalCodeError = false
                        },
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        singleLine = true,
                        label = { Text(text = "Postal Code") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
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
                    Spacer(modifier = Modifier.height(10.dp))


                    OutlinedTextField(
                        value = address,
                        onValueChange = {
                            address = it
                            addressError = false
                        },
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
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

                    Spacer(modifier = Modifier.height(20.dp))

                    if (!isLoading) {
                        Button(
                            onClick = {
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
                                if (address.text.isEmpty()) {
                                    addressError = true
                                }
                                if (postalCode.text.isEmpty()) {
                                    postalCodeError = true
                                }
                                if (firstNameError || lastNameError || phoneError
                                    || userNameError || addressError
                                    || postalCodeError
                                ) {
                                    return@Button
                                }
                                var userInfo = UserVM(
                                    id = currentUser.value!!.userId,
                                    customerId = currentUser.value!!.customerId,
                                    username = userName.text,
                                    firstName = firstName.text,
                                    lastName = lastName.text,
                                    address = address.text,
                                    phone = phone.text,
                                    postalCode = postalCode.text
                                )
                                isLoading = true

                                userViewModel.update(userInfo) { response ->
                                    if (response.status == "OK") {
                                        CoroutineScope(Dispatchers.IO).launch {
                                            val userEntity = userInfo.convertToEntity()
                                            userEntity.id = currentUser.value!!.id
                                            userEntityViewModel.update(userEntity)
                                        }
                                        isLoading = false
                                        navController.popBackStack()
                                    }
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
                                text = "Update Profile",
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