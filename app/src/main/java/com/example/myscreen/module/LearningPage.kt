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
            put("Mastering art of Saving", Routes.CreditPage)
            put("Savings Goals", Routes.CreditPage)
            put("Needs or Wants?", Routes.CreditPage)
            put("Savings Account", Routes.SavingAccount)
            put("Track Saving Goals", Routes.CreditPage)
            put("Explore Interests", Routes.CreditPage)
            put("Financial Terms", Routes.CreditPage)
            put("Exploring Savings Accounts", Routes.TypesOfSavings)
            put("Long term goals", Routes.CreditPage)
            put("Explore Investing", Routes.CreditPage)
            put("Credit & Debt management", Routes.CreditPage)
        }
    }

    val youtubeLinkMap = remember {
        mutableMapOf<Int, String>().apply {
            // Define your mapping here
            put(R.drawable.piggy, "https://www.youtube.com/watch?v=JkCmIxraWlM")
            put( R.drawable.allowance, "https://www.youtube.com/watch?v=NfurkrZEn3Q")
            put( R.drawable.shortgoal, "https://www.youtube.com/watch?v=v-mlEQ7KW5Q&t=4s")
            put( R.drawable.needswants, "https://www.youtube.com/watch?v=9ZxpWI1rDTE")
            put(R.drawable.savingsac, "https://www.youtube.com/watch?v=HsXAvH3D7wY")
            put( R.drawable.savetrack, "https://www.youtube.com/watch?v=Duxo4xXeMec")
            put(R.drawable.interest, "https://www.youtube.com/watch?v=8edPzh71RIQ")
            put( R.drawable.financeterms, "https://www.youtube.com/watch?v=E2wcbUNZ-yo")
            put( R.drawable.typesofsaving, "https://www.youtube.com/watch?v=fzAoV0rkizI")
            put(  R.drawable.longsave, " https://www.youtube.com/watch?v=jLojCtQPmbk")
            put(R.drawable.investing, "https://www.youtube.com/watch?v=qIw-yFC-HNU")
            put( R.drawable.debtmanage, "https://www.youtube.com/watch?v=Wh44hyYLnS4")
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
                fontFamily = FontFamily.Serif, color = bluee
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
