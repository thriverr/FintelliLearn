package com.example.myscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myscreen.ui.theme.BgBlueColor
import com.example.myscreen.ui.theme.Purple_200



@Composable
fun FintelliLearn(navController: NavController) {
    // Define the background color for the entire screen

    val background= BgBlueColor
    // State variables for button clicks
    var isClicked1 by remember { mutableStateOf(false) }
    var isClicked2 by remember { mutableStateOf(false) }

    // Set up the screen with the desired background color
    Box(
        modifier = Modifier
            .fillMaxSize().background(background)
            // Set the background color
            .padding(16.dp) // Add padding around the screen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(), // Column takes the whole size of the screen
            verticalArrangement = Arrangement.Center, // Center children vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center children horizontally
        ) {
            // Large bold text centered in the screen
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center // Evenly space the buttons across the row
            ){
                Text(
                    text = "Welcome to",
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif,
                    fontSize = 32.sp, // Large text size in sp units
                    modifier = Modifier.padding(bottom = 12.dp) // Add padding below the text
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center // Evenly space the buttons across the row
            ){
                Text(
                    text = "Finshaala!",
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic ,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp, // Large text size in sp units
                    modifier = Modifier.padding(bottom = 12.dp) // Add padding below the text
                )
            }

            // Spacer between the text and the buttons
            Spacer(modifier = Modifier.height(32.dp))

            // Row containing the buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly // Evenly space the buttons across the row
            ) {
                // First button with dynamic color change and navigation on click
                Button(
                    onClick = {
                        isClicked1 = !isClicked1
                        navController.navigate(Routes.SignInPage) // Navigate to SignInPage
                    },
                    shape = RectangleShape, // Rounded corners
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isClicked1) Color.White else Purple_200 // Change colors based on click state
                    ),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Login")
                }

                // Second button with dynamic color change and navigation on click
                Button(
                    onClick = {
                        isClicked2 = !isClicked2
                        navController.navigate(Routes.SignUpPage) // Navigate to SignUpPage
                    },
                    shape = RectangleShape, // Rounded corners
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isClicked2) Color.White else Purple_200 // Change colors based on click state
                    ),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Sign Up")
                }
            }
        }
    }
}
