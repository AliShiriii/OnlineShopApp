package com.example.onlineshopapp.ui.screens

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshopapp.db.viewModels.UserEntityViewModel
import com.example.onlineshopapp.ui.components.AdvancedButton
import com.example.onlineshopapp.ui.theme.Aqua
import com.example.onlineshopapp.ui.theme.LightBlue
import com.example.onlineshopapp.ui.theme.Orange
import com.example.onlineshopapp.ui.theme.Red
import com.example.onlineshopapp.utils.ThisApp

@Composable
fun DashboardScreen(navController: NavHostController, userEntityViewModel: UserEntityViewModel) {

    var currentUser by remember { mutableStateOf(userEntityViewModel.currentUser) }

    Column {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Text(
                    text = "Use Profile", fontSize = 20.sp, textAlign = TextAlign.Center,
                    modifier = Modifier.padding(0.dp, 9.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))

            }
        }

        Row(Modifier.padding(20.dp)) {

            Card(
                modifier = Modifier.padding(0.dp),
                shape = RoundedCornerShape(25.dp),
                backgroundColor = Color.LightGray
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "",
                    Modifier.size(80.dp),
                    tint = Color.White
                )
            }

            Spacer(
                modifier = Modifier
                    .size(15.dp)
            )

            Column(modifier = Modifier.weight(1f))
            {
                Text(
                    text = "${currentUser.value?.firstName} ${currentUser.value?.lastName}",
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${currentUser.value?.username}",
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Edit, contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Account", Modifier.padding(20.dp), fontSize = 22.sp)

        LazyColumn {
            item {
                AdvancedButton(
                    title = "Invoices",
                    subTitle = "Show Your Invoices",
                    imageVector = Icons.Filled.Star,
                    iconBackgroundColor = LightBlue
                ) {
                    ThisApp.userId = currentUser.value?.userId!!
                    ThisApp.token = currentUser.value?.token!!

                    navController.navigate("invoices")
                }
            }

            item {
                AdvancedButton(
                    title = "About",
                    subTitle = "About The Application",
                    imageVector = Icons.Filled.Favorite,
                    iconBackgroundColor = Aqua
                ) {

                }
            }

            item {
                AdvancedButton(
                    title = "Help",
                    subTitle = "Help And Feedback",
                    imageVector = Icons.Filled.Info,
                    iconBackgroundColor = Orange
                ) {

                }
            }

            item {
                AdvancedButton(
                    title = "Logout ",
                    subTitle = "Logout",
                    imageVector = Icons.Filled.ExitToApp,
                    iconBackgroundColor = Red
                ) {

                }
            }
        }
    }
}