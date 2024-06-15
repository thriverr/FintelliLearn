package com.example.myscreen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myscreen.R
import com.example.myscreen.calculators.Button
import com.example.myscreen.navigation.Routes
import com.example.myscreen.ui.theme.lightteal

@Composable
fun AgeScreen(navController: NavController){
    val backgroundColor = lightteal

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Button(
            text = "  Junior Scholars: Age(12-18)",
            iconId = R.drawable.kidsmodule,
            onClick = { navController.navigate(Routes.KidsScreen) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            text = "   Financial Mastery Zone: Age(18-60)",
            iconId = R.drawable.adultmodule,
            onClick = { navController.navigate(Routes.AdultScreen) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            text = " Retire Ease: Age(60+)",
            iconId = R.drawable.retirementmodule,
            onClick = { navController.navigate(Routes.AdultScreen) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAgeScreen() {
    // Create a dummy NavController for preview
    val navController = rememberNavController()
    AgeScreen(navController)
}