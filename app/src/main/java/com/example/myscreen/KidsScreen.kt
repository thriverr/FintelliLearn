package com.example.myscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myscreen.ui.theme.BgBlueColor
import com.example.myscreen.ui.theme.BlueColor
import com.example.myscreen.ui.theme.GreenColor
import com.example.myscreen.ui.theme.Purple_200
import com.example.myscreen.ui.theme.YellowColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KidsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TopAppBar(
                    title = { Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Financial Literacy ")
                    } },
                    navigationIcon = {
                        IconButton(onClick = { /* Handle home icon click */ }
                            ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home",
                                modifier = Modifier.graphicsLayer(

                                    shape = RoundedCornerShape(16.dp)
                                )
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Handle logout icon click */ }) {
                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = "Logout",
                                modifier = Modifier.graphicsLayer(

                                    shape = RoundedCornerShape(16.dp)
                                )
                            )
                        }
                    },
                )
                Spacer(modifier = Modifier.height(50.dp))

            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(100.dp))

                Button(
                    onClick = { /* Handle button 1 click */ },
                    modifier = Modifier
                        .height(220.dp)
                        .width(250.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(BlueColor)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Savings ",style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold , color = Color.Black)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Unlocking Financial Freedom:Building Strong Saving Habits",
                            color = Color.Black)
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { /* Handle button 2 click */ },
                    modifier = Modifier
                        .height(220.dp)
                        .width(250.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(GreenColor)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){ Text(text = "Budgeting ",style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold , color = Color.Black)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Master Money Management:Essential Skills for Effective Budgeting",
                            color = Color.Black)}

                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { /* Handle button 3 click */ },
                    modifier = Modifier
                        .height(220.dp)
                        .width(250.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(YellowColor)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){Text(text = "Quiz ",style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold
                        , color = Color.Black)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Test your Financial Savvy: Dive into interactive Finanace Quizzes"
                            , color = Color.Black)}

                }
            }

        }
    )
}