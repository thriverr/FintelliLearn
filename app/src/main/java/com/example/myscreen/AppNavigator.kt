package com.example.myscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.myscreen.ui.theme.BgBlueColor

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    // Define the navigation graph using NavHost
    NavHost(navController = navController, startDestination =Routes.FintelliLearn) {
        // Sign-in page destination
        composable(Routes.SignInPage) {
            SignInPage(navController = navController)
        }

        // Sign-up page destination
        composable(Routes.SignUpPage) {
            SignUpPage(navController = navController)
        }

        // Welcome page destination
        composable(Routes.WelcomePage) {
            WelcomePage()
        }
        composable(Routes.FintelliLearn) {
           FintelliLearn(navController)
        }
    }
}





@Preview(showBackground = true)
@Composable
fun AppNavigatorPreview() {
    AppNavigator()
}