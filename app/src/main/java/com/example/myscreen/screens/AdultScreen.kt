package com.example.myscreen.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myscreen.navigation.Routes
import com.example.myscreen.ui.theme.BlueColor
import com.example.myscreen.ui.theme.GreenColor
import com.example.myscreen.ui.theme.YellowColor
import com.example.myscreen.ui.theme.darkgrey
import com.example.myscreen.ui.theme.lavender
import com.example.myscreen.ui.theme.peach

@Composable
fun MyButton(
    text: String,
    description1: String,
    description2: String,
    color: Color,
    onClick: () -> Unit,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .height(180.dp)
            .width(200.dp)
            .padding(8.dp)
    ) {
        androidx.compose.material3.Button(
            onClick = onClick,
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(color)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    fontSize=22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = description1,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = description2,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun MyLazyColumn(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(100.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = " Financial Mastery",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    fontSize = 38.sp
                )
                Text(
                    text = "Zone!",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    fontSize = 38.sp
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MyButton(

                    text = "Savings ",
                    description1 = "Building Strong ",
                    description2 = "Saving Habits ",
                    color = BlueColor,
                    navController = navController,
                    onClick = { /* Define action for clicking on Quiz button */ }
                )
                MyButton(
                    text = "Budgeting",
                    description1 = "Master Money",
                    description2 ="Management Skills" ,
                    color = lavender,
                    navController = navController,
                    onClick = { /* Define action for clicking on Quiz button */ }
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MyButton(
                    text = "Quiz",
                    description1 = "Dive into interactive",
                    description2 = " Finance Quizzes",
                    color = YellowColor,
                    navController = navController,
                    onClick = {navController.navigate(Routes.quiz) }
                )
                MyButton(
                    text = "Explore",
                    description1 = "Discover More",
                    description2 = " Financial Resource",
                    color = GreenColor,
                    navController = navController,
                    onClick = { /* Define action for clicking on Quiz button */ }
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MyButton(
                    text = "Retirement Planning",
                    description1 = "Plan your retirement",
                    description2 = "for secure future",
                    color = peach,
                    navController = navController,
                    onClick = { /* Define action for clicking on Quiz button */ }

                )
                MyButton(
                    text = "Investment",
                    description1 = "Invest for better",
                    description2 = "financial wealth",
                    color = darkgrey,

                    navController = navController,
                    onClick = { /* Define action for clicking on Quiz button */ }
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdultScreen(navController: NavController) {
    Scaffold {
        MyLazyColumn(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun AdultScreenPreview() {
    // Create a sample NavController for the preview
    val navController = rememberNavController()
    AdultScreen(navController = navController)
}