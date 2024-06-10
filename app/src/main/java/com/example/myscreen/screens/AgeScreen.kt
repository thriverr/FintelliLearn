package com.example.myscreen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myscreen.navigation.Routes
import com.example.myscreen.ui.theme.BgBlueColor
import com.example.myscreen.ui.theme.Purple_200

@Composable
fun AgeScreen(navController: NavController){
    val backgroundColor = BgBlueColor
    Column(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)
    ) {
        Spacer(modifier = Modifier.size(100.dp))

        Row(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),

            horizontalArrangement = Arrangement.Center) {
            var isClicked1 by remember { mutableStateOf(false) }
            var isClicked2 by remember { mutableStateOf(false) }

            // Define colors for the button based on the state (clicked or not clicked)

            Button(onClick = { isClicked1 = !isClicked1
                navController.navigate(Routes.KidsScreen)
                             }, modifier = Modifier
                .weight(1f)
                .padding(18.dp)
                .heightIn(150.dp)
                , shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isClicked1) Color.LightGray else Purple_200
                )
            ) {
                Text(text = "Junior Scholars      (Age 12-18)")
            }
            Spacer(modifier = Modifier.size(25.dp))
            Button(onClick = { isClicked2 = !isClicked2
                navController.navigate(Routes.AdultScreen)}, modifier = Modifier
                .weight(1f)
                .padding(18.dp)
                .heightIn(150.dp)
                , shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isClicked2) Color.LightGray else Purple_200
                )
            ) {
                Text(text = "Financial Mastery Zone      (Age 19+)")
            }
        }}
}
