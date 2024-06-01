package com.example.myscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myscreen.ui.theme.Purple40
import com.example.myscreen.ui.theme.Purple80
import com.example.myscreen.ui.theme.Purple_200
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun AboutProfile(userId: String?){


        val firestore = FirebaseFirestore.getInstance()
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var dob by remember { mutableStateOf("") }
        var profession by remember { mutableStateOf("") }
        var finshaaalaId by remember { mutableStateOf("") }
        var isEditing by remember { mutableStateOf(false) }

        LaunchedEffect(userId) {
            userId?.let {
                firestore.collection("users").document(it).get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            firstName = document.getString("firstName") ?: ""
                            lastName = document.getString("lastName") ?: ""
                            email = document.getString("email") ?: ""
                            dob = document.getString("dob") ?: ""
                            profession = document.getString("profession") ?: ""
                            finshaaalaId = document.getString("finshaaalaId") ?: ""
                        }
                    }
                    .addOnFailureListener { exception ->
                        println("Firestore error: ${exception.message}")
                    }
            }
        }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),


            ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(56.dp))
                Box(
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp)
                        .clip(CircleShape)
                        .background(Purple80)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = firstName.take(1).toUpperCase(),
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        fontSize = 38.sp,
                        color = Purple40
                    )
                }
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Welcome, $firstName $lastName!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center

                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Email: $email",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (isEditing) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.CalendarToday,
                            contentDescription = "Select Date"
                        )
                        TextField(
                            value = dob,
                            onValueChange = { dob = it },
                            label = { Text("dd/mm/yyyy") })


                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = profession,
                        onValueChange = { profession = it },
                        label = { Text("Profession") })
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = finshaaalaId,
                        onValueChange = { finshaaalaId = it },
                        label = { Text("Finshaala_id") })
                    Spacer(modifier = Modifier.height(16.dp))
                    TextButton(onClick = {
                        // Save data to Firestore
                        firestore.collection("users").document(userId ?: "").update(
                            mapOf(
                                "dob" to dob,
                                "profession" to profession,
                                "finshaalaId" to finshaaalaId
                            )
                        ).addOnSuccessListener {
                            // Data saved successfully
                        }.addOnFailureListener { exception ->
                            // Handle Firestore error
                            println("Firestore error: ${exception.message}")
                        }
                        // Toggle editing mode
                        isEditing = false
                    }) {
                        Text(text = "Save")
                    }
                } else {
                    // Non-editable fields
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Date of Birth: $dob",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Profession: $profession",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Finshaaala ID: $finshaaalaId",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(modifier = Modifier.background(Purple_200), contentAlignment = Alignment.Center){
                        TextButton(onClick = { isEditing = true }) {
                            Text(text = "Edit", textAlign = TextAlign.Center, fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold, color = Color.White )
                        }
                    }

                }

            }
        }
    }

    }