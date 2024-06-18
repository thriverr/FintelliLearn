package com.example.myscreen.module





import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myscreen.R
import com.example.myscreen.navigation.Routes
import com.example.myscreen.ui.theme.Pink80
import com.example.myscreen.ui.theme.advancecolor
import com.example.myscreen.ui.theme.beginnercolor
import com.example.myscreen.ui.theme.bluee
import com.example.myscreen.ui.theme.intermediatecolor
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore


@Composable

fun TableBudget(levels: List<Pair<String, List<Triple<String, String, Int>>>>, navController: NavController) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    val destinationMap = remember {
        mutableMapOf<String, String>().apply {
            // Define your mapping here
            put("What is Budget?", Routes.IntroBudgeting)
            put("Learn About Tracking", Routes.CreditPage)
            put("Create Your Budget", Routes.CreditPage)
            put("Remove Unnecessary Expenses", Routes.CreditPage)
            put("Have A Budgeting Plan!", Routes.SavingsAccount)
            put("Budgeting Methods", Routes.CreditPage)
            put("Handle Irregular Expenses", Routes.CreditPage)
            put("Emergency Funds", Routes.CreditPage)
            put("Planning Future Expenses", Routes.TypesOfSavings)
            put("Strategies to Repay Debt", Routes.CreditPage)
            put("Know About Self Employment", Routes.CreditPage)
            put("Reduce Tax Liabilities", Routes.CreditPage)
        }
    }

    val youtubeLinkMap = remember {
        mutableMapOf<Int, String>().apply {
            // Define your mapping here
            put (R.drawable.introbudget, "https://www.youtube.com/watch?v=CbhjhWleKGE")
            put( R.drawable.trackingfinance, "https://www.youtube.com/watch?v=NfurkrZEn3Q")
            put( R.drawable.basicbudget, "https://www.youtube.com/watch?v=v-mlEQ7KW5Q&t=4s")
            put( R.drawable.cuttingexpenses, "https://www.youtube.com/watch?v=9ZxpWI1rDTE")
            put(R.drawable.budgetingplan, "https://www.youtube.com/watch?v=Epk1SQr9lmE")
            put( R.drawable.budgetingrule, "https://www.youtube.com/watch?v=Duxo4xXeMec")
            put(R.drawable.irregularexpense, "https://www.youtube.com/watch?v=8edPzh71RIQ")
            put( R.drawable.emergency, "https://www.youtube.com/watch?v=E2wcbUNZ-yo")
            put( R.drawable.forecasting, "https://www.youtube.com/watch?v=Awm_LxHbHHE")
            put(  R.drawable.strategiesbudgeting, " https://www.youtube.com/watch?v=jLojCtQPmbk")
            put(R.drawable.selfemployment, "https://www.youtube.com/watch?v=qIw-yFC-HNU")
            put( R.drawable.taxplanning, "https://www.youtube.com/watch?v=Wh44hyYLnS4")
        }
    }

    var expandedLevel by remember { mutableStateOf<String?>(null) }
    val firestore = Firebase.firestore
    val userId = Firebase.auth.currentUser?.uid ?: ""
    val userDocument = firestore.collection("users").document(userId)
    var progress by remember { mutableStateOf(0f) }
    val totalTopics = levels.sumOf { it.second.size }

    // Load user progress initially
    LaunchedEffect(userId) {
        userDocument.get().addOnSuccessListener { document ->
            if (document.exists()) {
                // Retrieve progress if document exists
                val progressMap = document.data?.get("budgetStatus") as? Map<String, Boolean>
                if (progressMap != null) {
                    // Calculate initial progress based on stored values
                    val markedCount = progressMap.count { it.value }
                    progress = markedCount.toFloat() / totalTopics
                }
            } else {
                // Initialize progress if document doesn't exist
                userDocument.set(mapOf("budgetStatus" to emptyMap<String, Boolean>()))
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.size(100.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Budgeting",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default, color = Color.Black,
                style = TextStyle(

                    textDecoration = TextDecoration.Underline
                ),
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .weight(1f) // This will make the progress indicator take all available width
                    .height(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp)) // Add space between the progress bar and the text
            Text(
                text = "${(progress*100).toInt()}%", // Display progress as percentage
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.size(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Pink80),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Topics", fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f).align(Alignment.CenterVertically).padding(start=18.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Reading Reference", fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f).padding(top=12.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Watch & Learn", fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f).padding(end=12.dp)
            )
        }


        levels.forEach { (level, data) ->
            val expanded = level == expandedLevel

            Column {
                val bgColor = if (expanded) {
                    when (level) {
                        "Beginner" -> beginnercolor // Blue for buttons
                        "Intermediate" -> intermediatecolor
                        "Advance" -> advancecolor
                        else -> bluee // Default color if none of the above levels match
                    }
                    // Background color when expanded
                } else {
                    bluee
                }

                Button(
                    onClick = {
                        expandedLevel = if (expanded) null else level
                    },
                    colors = ButtonDefaults.buttonColors(
                        bgColor,
                        contentColor = Color.White // Text color when button is colored
                    ),
                    shape = RectangleShape,
                    modifier = Modifier

                        .padding(vertical = 12.dp)

                        .padding(horizontal = 16.dp).padding(start=80.dp,end=80.dp).fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp),

                    ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = level,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,

                            )
                        Icon(
                            imageVector = if (expanded) Icons.Outlined.ArrowDropUp
                            else Icons.Outlined.ArrowDropDown,
                            contentDescription = "Expand",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                if (expanded) {
                    LazyColumn {
                        items(data) { rowData ->
                            var isMarked by remember { mutableStateOf(false) }

                            // Load the topic status from Firestore
                            LaunchedEffect(rowData.first) {
                                userDocument.get().addOnSuccessListener { document ->
                                    val progressMap = document.data?.get("budgetStatus") as? Map<String, Boolean>
                                    if (progressMap != null) {
                                        isMarked = progressMap[rowData.first] ?: false
                                    }
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { /* Handle row click */ }
                                    .padding(vertical = 12.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = rowData.first, fontSize = 14.sp,
                                    modifier = Modifier.weight(2f).padding(4.dp),
                                    fontWeight = FontWeight.Bold
                                )

                                val destination = destinationMap[rowData.second]
                                    ?: "DefaultDestination"
                                Text(
                                    text = rowData.second, fontSize = 14.sp,
                                    modifier = Modifier
                                        .weight(2f).padding(4.dp)
                                        .clickable { navController.navigate(destination) }
                                )
                                val youtubeLink = youtubeLinkMap[rowData.third]
                                if (youtubeLink != null) {
                                    Image(
                                        painter = painterResource(id = rowData.third),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .weight(1f)
                                            .size(32.dp)
                                            .clickable {
                                                val intent = Intent(
                                                    Intent.ACTION_VIEW,
                                                    Uri.parse(youtubeLink)
                                                )
                                                launcher.launch(intent)
                                            }
                                    )
                                }
                                Checkbox(
                                    checked = isMarked,
                                    onCheckedChange = { checked ->
                                        isMarked = checked
                                        userDocument.update("budgetStatus.${rowData.first}", checked)
                                            .addOnSuccessListener {
                                                // Recalculate progress based on marked topics
                                                userDocument.get().addOnSuccessListener { document ->
                                                    val progressMap = document.data?.get("budgetStatus") as? Map<String, Boolean>
                                                    if (progressMap != null) {
                                                        val markedCount = progressMap.count { it.value }
                                                        progress = markedCount.toFloat() / totalTopics
                                                    }
                                                }
                                            }
                                    },
                                    modifier = Modifier.weight(0.5f)
                                )

                            }
                        }
                    }
                }
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun PreviewTableBudget() {
    val navController = rememberNavController()
    val sampleLevels = listOf(
        "Beginner" to listOf(
            Triple("Introduction to Saving", "The power of Saving", R.drawable.piggy),
            Triple("How to Save", "Mastering art of Saving", R.drawable.allowance)
        ),
        "Intermediate" to listOf(
            Triple("Savings Goals", "Savings Goals", R.drawable.shortgoal),
            Triple("Needs or Wants?", "Needs or Wants?", R.drawable.needswants)
        ),
        "Advance" to listOf(
            Triple("Long term goals", "Long term goals", R.drawable.longsave),
            Triple("Explore Investing", "Explore Investing", R.drawable.investing)
        )
    )
    Table(levels = sampleLevels, navController = navController)
}