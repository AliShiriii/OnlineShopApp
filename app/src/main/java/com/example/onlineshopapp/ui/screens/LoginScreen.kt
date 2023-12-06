package com.example.onlineshopapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
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
import com.example.onlineshopapp.ui.theme.Dark
import com.example.onlineshopapp.viewModels.customer.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    var userName by remember { mutableStateOf(TextFieldValue()) }
    var userNameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    Column {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
        }
    }

    LazyColumn {
        item {

        }
        item {
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Login", fontSize = 20.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(5.dp))
        }

        item {
            Column(
                Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Text(text = "Let's sign you in.", fontSize = 28.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Let's sign you in.", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "You've been missed.", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(20.dp))


                OutlinedTextField(
                    value = userName,
                    onValueChange = {
                        userName = it
                        userNameError = false
                    },
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
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = false
                    },
                    singleLine = true,
                    label = { Text(text = "Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
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
                Spacer(modifier = Modifier.height(20.dp))

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
                } else {
                    Button(
                        onClick = {

                            if (userName.text.isEmpty()) {
                                userNameError = true
                            }
                            if (password.text.isEmpty()) {
                                passwordError = true
                            }
                            if (userNameError || passwordError) return@Button
                            isLoading = true
                            userViewModel.login(
                                UserVM(username = userName.text, password = password.text)
                            ) { response ->
                                isLoading = false
                                if (response.status == "OK") {
                                    val user = response.data!![0]
                                    CoroutineScope(Dispatchers.IO).launch {
                                        userEntityViewModel.insert(user.convertToEntity())
                                    }

                                    Toast.makeText(
                                        context,
                                        "Welcome back${user.firstName}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    navController.navigate("home")
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
                            text = "Login",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                    }
                }
            }
        }
    }
}