package com.example.myscreen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AppNavigator(auth: FirebaseAuth, db: FirebaseFirestore) {
    val navController = rememberNavController()

    // Define the navigation graph using NavHost
    NavHost(navController = navController, startDestination =Routes.FintelliLearn) {
        // Sign-in page destination
        composable(Routes.SignInPage) {
            SignInPage(navController = navController)
        }

        // Sign-up page destination
        composable(Routes.SignUpPage) {
            SignUpPage(navController = navController,auth=auth)
        }

        // Welcome page destination
        composable(
           Routes.WelcomePage
        ) {
WelcomePage(auth = auth, db =db )
        }
        composable(Routes.FintelliLearn) {
           FintelliLearn(navController)
        }
    }
}





