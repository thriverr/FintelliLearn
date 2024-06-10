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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myscreen.R
import com.example.myscreen.navigation.Routes
import com.example.myscreen.ui.theme.bluee


@Composable

fun Table(levels: List<Pair<String, List<Triple<String, String, Int>>>>, navController: NavController) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    val destinationMap = remember {
        mutableMapOf<String, String>().apply {
            // Define your mapping here
            put("The power of Saving", Routes.ImpOfSavingArticle)
            put("How do you Save?", Routes.CreditPage)
            put("Setting Savings Goals", Routes.CreditPage)
            put("Creating A budget with Savings", Routes.CreditPage)
            put("Emergency Fund", Routes.CreditPage)
            put("Types Of Savings Accounts", Routes.CreditPage)
            put("Savings on Everyday Expenses", Routes.CreditPage)
            put("Credit vs Debit", Routes.CreditPage)
        }
    }

    val youtubeLinkMap = remember {
        mutableMapOf<Int, String>().apply {
            // Define your mapping here
            put(R.drawable.piggy, "https://youtu.be/JqYoLQXO7j4?si=7DOfuq7GwjzLURmJ")
            put(R.drawable.howdoyousave, "https://youtu.be/JqYoLQXO7j4?si=7DOfuq7GwjzLURmJ")
            put(R.drawable.settinggoals, "https://youtu.be/JqYoLQXO7j4?si=7DOfuq7GwjzLURmJ")
            put(R.drawable.budgetsaving, "https://youtu.be/JqYoLQXO7j4?si=7DOfuq7GwjzLURmJ")
            put(R.drawable.emergencyfund, "https://youtu.be/JqYoLQXO7j4?si=7DOfuq7GwjzLURmJ")
            put(R.drawable.typesofsaving, "https://youtu.be/JqYoLQXO7j4?si=7DOfuq7GwjzLURmJ")
            put(R.drawable.savingeverydayexpenses, "https://youtu.be/JqYoLQXO7j4?si=7DOfuq7GwjzLURmJ")
            put(R.drawable.creditvsdebit, "https://youtu.be/JqYoLQXO7j4?si=7DOfuq7GwjzLURmJ")
        }
    }

    var expandedLevel by remember { mutableStateOf<String?>(null) }
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.size(100.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Savings",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }
        Spacer(modifier = Modifier.size(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Topics", fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Reading Reference", fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Watch & Learn", fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
        }

        levels.forEach { (level, data) ->
            val expanded = level == expandedLevel

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expandedLevel = if (expanded) null else level
                        }
                        .padding(vertical = 12.dp).background(bluee),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = level,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold, color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                    Icon(
                        imageVector = if (expanded) Icons.Outlined.ArrowDropUp else Icons.Outlined.ArrowDropDown,
                        contentDescription = "Arrow", tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                if (expanded) {
                    LazyColumn {
                        items(data) { rowData ->
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
                            }
                        }
                    }
                }
            }
        }
    }
}
