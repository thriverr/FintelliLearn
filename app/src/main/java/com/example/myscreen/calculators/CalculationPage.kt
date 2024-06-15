package com.example.myscreen.calculators



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myscreen.R
import com.example.myscreen.navigation.Routes

@Composable

fun CalculationPage(navController: NavController) {
    LazyColumn {

        item {
            Spacer(modifier = Modifier.height(60.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Choose your Financial",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 14.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }
        }
        item {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Calculator",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 7.dp),
                    fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Button(
                text = "Compound Calculator",
                iconId = R.drawable.cicalci,
                onClick = { navController.navigate(Routes.CompoundInterestCalculator) }
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Button(
                text = "Retirement Calculator",
                iconId = R.drawable.retirementcalci,
                onClick = { /* Handle click */ }
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Button(
                text = "Inflation Calculator",
                iconId = R.drawable.inflationcalci,
                onClick = { /* Handle click */ }
            )
        }
    }
}

@Composable
fun Button(text: String, iconId: Int, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(290.dp)
        ) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )
        }
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier ,// Remove the comma here
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = FontFamily.Default,

                )
        }

    }
}

