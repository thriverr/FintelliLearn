package com.example.myscreen.calculators



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myscreen.navigation.Routes

@Composable
fun CalculationPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Text(
                text = "Choose a Financial",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 14.dp), fontWeight = FontWeight.Bold
            )


        }
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Text(
                text = "Financial Calculator",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 7.dp), fontWeight = FontWeight.Bold
            )


        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            headline = "Compound Interest Calculator",
            description = "Calculate compound interest of your investment or for how long can your investment money last with this calculator.",

            backgroundColor = Color.LightGray,
            onClick = {  navController.navigate(Routes.CompoundInterestCalculator) }
        )
        Spacer(modifier = Modifier.height(26.dp))
        Button(
            headline = "Investment Time Calculator",
            description = "Calculate the amount of time needed to grow an investment to a certian future value given annual interest rate.",
            backgroundColor = Color.LightGray,
            onClick = { /* Handle button 2 click */ }
        )

        Spacer(modifier = Modifier.height(26.dp))
        Button(
            headline = "Inflation Calculator",
            description = "Estimate the impact of inflation over time with our handy calculator. Simply input the initial amount and the years elapsed to see its adjusted value.",
            backgroundColor = Color.LightGray,
            onClick = { /* Handle button 2 click */ }
        )
    }
}

@Composable
fun Button(
    headline: String,
    description: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable(onClick = onClick),
        color = backgroundColor,
        shape = RoundedCornerShape(8.dp) // Adjust corner radius as needed
    ) {
        Column {
            Text(
                text = headline,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Spacer(
                modifier = Modifier.fillMaxWidth()
                    .height(2.dp).background(color = Color.Gray)

            )
            Text(
                text = description,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
