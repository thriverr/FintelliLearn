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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun AboutProfile( currentUser: FirebaseUser){


        val firestore = FirebaseFirestore.getInstance()
        var username by remember { mutableStateOf("") }
        //var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var dob by remember { mutableStateOf("") }
        var profession by remember { mutableStateOf("") }
        var finshaalaId by remember { mutableStateOf("") }
        var isEditing by remember { mutableStateOf(false) }
var totalScore by remember { mutableStateOf(0) }

    var userProfile by remember {
        mutableStateOf(
            User(
                username = "",
                email = "",
                dob = "",
                profession = "",
                finshaalaId = "",
                totalScore = 0
            )
        )
    }

    LaunchedEffect(currentUser.uid) {
        val userDocRef = firestore.collection("users").document(currentUser.uid)
        userDocRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    username = document.getString("username") ?: ""
                    email=document.getString("email") ?: ""
                    dob = document.getString("dob") ?: ""
                     profession = document.getString("profession") ?: ""
                    finshaalaId = document.getString("finshaalaId") ?: ""

                    userProfile = User(username, currentUser.email ?: "", dob, profession, finshaalaId)

                    firestore.collection("posts")
                        .whereEqualTo("userFirstName", username)
                        .get()
                        .addOnSuccessListener { result ->
                            totalScore = result.documents.sumOf { document ->
                                document.getLong("score")?.toInt() ?: 0
                            }
                            // Update the user profile with the total score
                            userProfile = userProfile.copy(totalScore = totalScore)
                        }
                        .addOnFailureListener { e ->
                            // Handle failure
                        }
                } else {
                    // Handle the case where the document doesn't exist
                }
            }
            .addOnFailureListener { e ->
                // Handle failure
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
                        text = username.take(1).toString(),
                        color = Purple40,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Welcome, $username !",
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
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Total Score: $totalScore",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (isEditing) {
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") }
                    )



                    Spacer(modifier = Modifier.height(16.dp))
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
                        value = finshaalaId,
                        onValueChange = { finshaalaId = it },
                        label = { Text("Finshaala_id") })
                    Spacer(modifier = Modifier.height(16.dp))
                    TextButton(onClick = {
                        // Save data to Firestore
                        firestore.collection("users").document(currentUser.uid ?: "").update(
                            mapOf(
                                "username" to username,
                                "dob" to dob,
                                "profession" to profession,
                                "finshaalaId" to finshaalaId
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
                        text = "User name: $username",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
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
                        text = "Finshaaala ID: $finshaalaId",
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


data class User(
    val username: String?,
    val email: String?,
    val dob: String,
    val profession: String,
    val finshaalaId: String,
    var totalScore: Int = 0
)