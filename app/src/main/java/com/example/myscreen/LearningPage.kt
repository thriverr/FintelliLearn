package com.example.myscreen



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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Table(data: List<Triple<String, String, Int>>,navController: NavController) {
    val destinationMap = remember {
        mutableMapOf<String, String>().apply {
            // Define your mapping here
            put("Learn About Saving", Routes.ImpOfSavingArticle)
            put("Credit", Routes.CreditPage)


        }
    }

    val youtubeLinkMap  = remember {
        mutableMapOf<Int, String>().apply {
            // Define your mapping here
            put(R.drawable.piggy, "https://youtu.be/JqYoLQXO7j4?si=7DOfuq7GwjzLURmJ")


        }
    }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.size(100.dp))
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center){Text(
            text = "Savings",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )}
        Spacer(modifier = Modifier.size(24.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
            horizontalArrangement = Arrangement.Center){

            Text(text = "Topics",fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "Reading Reference",fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "Watch & Learn",fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
        }

        LazyColumn {
            items(data) { rowData ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Handle row click */ }
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = rowData.first, fontSize = 14.sp, modifier = Modifier.weight(2f)
                    )

                    val destination = destinationMap[rowData.second] ?: "DefaultDestination"
                    Text(text = rowData.second, fontSize = 14.sp,
                        modifier = Modifier
                            .weight(2f)
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
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
                                    launcher.launch(intent)

                                }
                        )

                    }

                }
            }
        }
    }

}


