package com.example.myscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.myscreen.ui.theme.BgBlueColor
import com.example.myscreen.ui.theme.Purple_200

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInPage(navController: NavController) {
    // Local states to hold the email and password input values
    val background= BgBlueColor
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val auth = FirebaseAuthInstance.instance
    val outlineColor = Purple_200
    val focusedLabelColor = Purple_200
    // Column layout for the sign-in page
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.login), // Use your image resource here
            contentDescription = "Image", // Provide a content description for accessibility
            modifier = Modifier.size(250.dp) // Adjust size as needed
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(text = "Sign In", fontSize = 24.sp, fontFamily = FontFamily.Serif,
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
            ), colors = TextFieldDefaults.outlinedTextFieldColors(
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
                imeAction = ImeAction.Done
            ),colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = outlineColor, // Color when focused
                // Color when unfocused
                cursorColor = outlineColor, // Color of cursor

            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Sign-in button
        Button(
            onClick = {
                // Use Firebase Authentication to sign in
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Navigate to the welcome page if the sign-in is successful
                            navController.navigate(Routes.WelcomePage)
                        } else {
                            // Handle sign-in error (e.g., show an error message)
                            println("Sign-in error: ${task.exception?.message}")
                        }
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors =  ButtonDefaults.buttonColors(
                containerColor =  Purple_200
            )
        ) {
            Text("Sign In")
        }
        Spacer(modifier = Modifier.height(12.dp))
        val annotatedText = buildAnnotatedString {
            append("New to FintelliLearn? ")
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Sign up now!")
            }
        }

        // Display the annotated text
        BasicText(
            text = annotatedText,
            modifier = Modifier.clickable {
                // Perform any action you want when the text is clicked, if necessary
                // For example, you can navigate to the sign-up page
                navController.navigate(Routes.SignUpPage)
            }
        )
    }
}