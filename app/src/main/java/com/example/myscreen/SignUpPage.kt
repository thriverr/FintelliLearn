package com.example.myscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.myscreen.ui.theme.BgBlueColor
import com.example.myscreen.ui.theme.Purple_200

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpPage(navController: NavController) {

    val background= BgBlueColor
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val auth = FirebaseAuthInstance.instance
    val outlineColor = Purple_200
    val focusedLabelColor = Purple_200
    // Column layout for the sign-up page
    Column(
        modifier = Modifier
            .fillMaxSize().background(background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.signup), // Use your image resource here
            contentDescription = "Image", // Provide a content description for accessibility
            modifier = Modifier.size(250.dp) // Adjust size as needed
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(text = "Sign Up", fontSize = 24.sp, fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
            ,modifier = Modifier.padding(bottom = 32.dp))

        // Email input field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = focusedLabelColor) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = outlineColor, // Color when focused
                // Color when unfocused
                cursorColor = outlineColor, // Color of cursor

            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Password input field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = focusedLabelColor) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = outlineColor, // Color when focused
                // Color when unfocused
                cursorColor = outlineColor, // Color of cursor

            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Confirm password input field


        // Sign-up button
        Button(
            onClick = {
                // Use Firebase Authentication to sign up
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Navigate to the welcome page if the sign-up is successful
                            navController.navigate(Routes.WelcomePage)
                        } else {
                            // Handle sign-up error (e.g., show an error message)
                            println("Sign-up error: ${task.exception?.message}")
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            colors =  ButtonDefaults.buttonColors(
                containerColor =  Purple_200
            )
        ) {
            Text("Sign Up")
        }
    }
}