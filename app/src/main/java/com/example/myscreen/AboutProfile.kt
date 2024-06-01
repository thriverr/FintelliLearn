package com.example.myscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myscreen.ui.theme.PurpleGrey40
import com.example.myscreen.ui.theme.PurpleGrey80
import com.example.myscreen.ui.theme.Purple_200
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AboutProfile(userId: String?){
    val firestore = FirebaseFirestore.getInstance()
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    LaunchedEffect(userId) {
        userId?.let {
            firestore.collection("users").document(it).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        firstName = document.getString("firstName") ?: ""
                        lastName = document.getString("lastName") ?: ""
                        email = document.getString("email") ?: ""
                    }
                }
                .addOnFailureListener { exception ->
                    println("Firestore error: ${exception.message}")
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Spacer(modifier = Modifier.height(56.dp))
        Box(
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
                .clip(CircleShape)
                .background(PurpleGrey80)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = firstName.take(1).toUpperCase(),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                fontSize = 28.sp,
                color = Purple_200
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "Welcome, ${firstName} ${lastName}!",
            style = MaterialTheme.typography.headlineMedium,
                    fontSize =28.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Email: $email",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 24.sp,
            color = PurpleGrey40
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(

            text = "This is your personalised profile section",
            fontSize = 24.sp,
            color = PurpleGrey40,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}