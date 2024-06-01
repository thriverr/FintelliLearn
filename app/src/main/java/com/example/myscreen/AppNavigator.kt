package com.example.myscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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
        composable(
            "welcome_page/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            WelcomePage(userId = userId)
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