package com.example.myscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myscreen.ui.theme.BlueColor
import com.example.myscreen.ui.theme.GreenColor
import com.example.myscreen.ui.theme.YellowColor
import com.example.myscreen.ui.theme.lavender


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun KidsScreen(navController: NavController) {
    Scaffold {
        MLazyColumn()
    }
}
@Composable
fun Button(
    text: String,
    description: String,
    color: Color
) {
    Box(
        modifier = Modifier
            .height(180.dp)
            .width(200.dp)
            .padding(8.dp)
    ) {
        androidx.compose.material3.Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(color)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = description,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun MLazyColumn() {
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
                    text = "Financial Literacy for Young Minds",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    fontSize = 28.sp
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    text = "Savings",
                    description = "Unlocking Financial Freedom: Building Strong Saving Habits",
                    color = BlueColor
                )
                Button(
                    text = "Budgeting",
                    description = "Master Money Management: Essential Skills for Effective Budgeting",
                    color = lavender
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    text = "Quiz",
                    description = "Test your Financial Savvy: Dive into interactive Finance Quizzes",
                    color = YellowColor
                )
                Button(
                    text = "Explore More",
                    description = "Discover Additional Resources: Expand your financial knowledge",
                    color = GreenColor
                )
            }
        }


    }
}
