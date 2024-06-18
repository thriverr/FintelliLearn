package com.example.myscreen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Spacer(modifier = Modifier.height(92.dp))
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center){
            Text(text = "Empower Your Learning Journey",
                fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center){
            Text(text = "With FinShaala!",
                fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }

        Button(
            text = "  Junior Scholars",
            age="Age(12-18)",
            iconId = R.drawable.kidsmodule,
            onClick = { navController.navigate(Routes.KidsScreen) }
        )

        Button(
            text = "   Financial Mastery Zone",
            age="Age(18-60)",
            iconId = R.drawable.adultmodule,
            onClick = { navController.navigate(Routes.AdultScreen) }
        )



        Button(
            text = " Retire Ease",
            age="Age(60+)",
            iconId = R.drawable.retirementmodule,
            onClick = { navController.navigate(Routes.RetireScreen) }
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