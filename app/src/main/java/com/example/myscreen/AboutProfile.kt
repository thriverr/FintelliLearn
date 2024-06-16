package com.example.myscreen

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Score
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myscreen.ui.theme.Purple40
import com.example.myscreen.ui.theme.Purple80
import com.example.myscreen.ui.theme.lightgrey
import com.example.myscreen.ui.theme.medGrey
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun AboutProfile( currentUser: FirebaseUser){


        val firestore = FirebaseFirestore.getInstance()
        var username by remember { mutableStateOf("") }

        var email by remember { mutableStateOf("") }
        var dob by remember { mutableStateOf("") }
        var profession by remember { mutableStateOf("") }
        var finshaalaId by remember { mutableStateOf("") }
        var isEditing by remember { mutableStateOf(false) }
        var totalScore by remember { mutableStateOf(0) }
    var topicStatus by remember { mutableStateOf(emptyMap<String, Boolean>()) }
    var budgetStatus by remember { mutableStateOf(emptyMap<String, Boolean>()) }

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
                    email = document.getString("email") ?: ""
                    dob = document.getString("dob") ?: ""
                    profession = document.getString("profession") ?: ""
                    finshaalaId = document.getString("finshaalaId") ?: ""

                    userProfile =
                        User(username, currentUser.email ?: "", dob, profession, finshaalaId)

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
                    @Suppress("UNCHECKED_CAST")
                    topicStatus = document.get("topicStatus") as Map<String, Boolean>? ?: emptyMap()
                    @Suppress("UNCHECKED_CAST")
                    budgetStatus = document.get("budgetStatus") as Map<String, Boolean>? ?: emptyMap()

                    // Calculate overall progress
                    val topicProgress = topicStatus.count { it.value }
                    val budgetProgress = budgetStatus.count { it.value }
                    val overallProgress = (topicProgress + budgetProgress).toFloat() / 48 // Assuming equal weight for both
                    userProfile = userProfile.copy(overallProgress = overallProgress)
                }

                else {
                // Handle the case where the document doesn't exist
            }
            }
            .addOnFailureListener { e ->
                // Handle failure
            }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()



    ) {


        // Colored box with rounded corners and circular box inside it
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = Purple80,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                ),

        ){
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = 50.dp)
                    .background(color = Purple40, shape = CircleShape),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = username.take(1).toString(),
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(56.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(8.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp),

        ){
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        ,


                    ) {
                    Column(
                        modifier = Modifier.padding(8.dp),

                    ) {
                        Spacer(modifier = Modifier.height(56.dp))
                        Box(modifier = Modifier, contentAlignment = Alignment.Center){
                            Text(
                                text = "      Welcome, $username !",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                fontSize = 28.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(26.dp))
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween, // Adjusted horizontal arrangement
                            modifier = Modifier.fillMaxWidth() ) {
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Icon(
                                    Icons.Default.Email,
                                    contentDescription = "Email"
                                )
                                Text(
                                    text = "Email: $email",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start, fontSize = 20.sp
                                )
                            }

                            IconButton(onClick = { isEditing = true }
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Edit",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit",
                                        tint = Color.Black
                                    )

                                }
                        }}
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(modifier=Modifier) {
                            Icon(
                                Icons.Default.Score,
                                contentDescription = "Coins"
                            )
                            Text(
                                text = "Total Score: $totalScore",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start, fontSize = 20.sp
                            )

                            }




                        // Circular Progress Tracker

                        if (isEditing) {
                            Spacer(modifier = Modifier.height(16.dp))
                            TextField(
                                value = username,
                                onValueChange = { username = it },
                                label = { Text("Username") },
                                textStyle = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
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
                                    label = { Text("dd/mm/yyyy") },
                                    textStyle = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold
                                    ),

                                )


                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            TextField(
                                value = profession,
                                onValueChange = { profession = it },
                                label = { Text("Profession") },
                                textStyle = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            TextField(
                                value = finshaalaId,
                                onValueChange = { finshaalaId = it },
                                label = { Text("Finshaala_id") },
                                textStyle = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                            )
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
                            },modifier=Modifier.background(lightgrey)) {
                                Text(text = "Save")
                            }
                        } else {
                            // Non-editable fields
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement =Arrangement.Center) {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "username"
                                )
                                Text(
                                    text = "User name: $username",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black,
                                     fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start, fontSize = 20.sp
                                )}
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement =Arrangement.Center) {
                                Icon(
                                    Icons.Default.CalendarToday,
                                    contentDescription = "Select Date"
                                )
                                Text(
                                    text = "Date of Birth: $dob",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start, fontSize = 20.sp
                                )}
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement =Arrangement.Center) {
                                Icon(
                                    Icons.Default.Work,
                                    contentDescription = "Profession"
                                )
                                Text(
                                    text = "Profession: $profession",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start, fontSize = 20.sp
                                )}
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement =Arrangement.Center) {
                                Icon(
                                    Icons.Default.PermIdentity,
                                    contentDescription = "UserId"
                                )
                                Text(
                                    text = "Finshaaala ID: $finshaalaId",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start, fontSize = 20.sp
                                )}
                            Spacer(modifier = Modifier.height(16.dp))
                            Box(modifier = Modifier.align(Alignment.CenterHorizontally)){
                                CircularProgress(
                                    percentage = userProfile.overallProgress,
                                    strokeWidth = 18.dp,
                                    radius = 200.dp
                                )
                            }



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
    var totalScore: Int = 0,
    var overallProgress: Float = 0f
)

@Composable
fun CircularProgress(percentage: Float, strokeWidth: Dp, radius: Dp, modifier: Modifier = Modifier) {
    val sweepAngle = percentage * 360
    Canvas(
        modifier = modifier
            .size(radius)
            .padding(16.dp)
    ) {
        val canvasSize = size.minDimension
        val strokeWidthPx = strokeWidth.toPx()

        // Draw the full gray circle as the background
        drawCircle(
            color = medGrey,
            radius = canvasSize /2 - strokeWidthPx/19,
            style = Stroke(strokeWidthPx)
        )

        // Draw the progress arc on top of the gray circle
        drawArc(
            color = Purple40,
            startAngle = -90f,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(strokeWidthPx)
        )

        // Draw percentage text in the center
        val percentageText = "${(percentage * 100).toInt()}%"
        drawContext.canvas.nativeCanvas.apply {
            val textPaint = Paint().apply {
                color = android.graphics.Color.BLACK
                textSize = 40.sp.toPx()
                textAlign = Paint.Align.CENTER
                isFakeBoldText = true
            }
            val xPos = size.width / 2
            val yPos = (size.height / 2 - (textPaint.descent() + textPaint.ascent()) / 2)
            drawText(percentageText, xPos, yPos, textPaint)
        }
    }
}
