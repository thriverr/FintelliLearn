package com.example.myscreen.screens

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myscreen.R
import com.example.myscreen.ui.theme.Purple_200
import com.example.myscreen.ui.theme.lightgrey

@Composable
fun RetireScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(lightgrey)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Add spacing between items
    ) {
        item {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = "Courses",
                fontSize = 26.sp,
                color = Purple_200,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 8.dp)

            )}
        }

        item {
            Text(
                text = "INSTRUCTION: We can help you navigate our courses through your voice.",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 14.dp)

            )
        }

        item {
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .padding(start = 75.dp)
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(Purple_200)
            ) {
                Text(
                    text = "Start Voice Recognition",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .clickable { /*TODO*/ }
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Understanding Annuity",
                        fontSize = 20.sp,
                        color = Purple_200,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.annuity), // Replace with your image resource
                        contentDescription = "Card 1 Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(170.dp)
                            .padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Annuity is a contract between an individual and an insurance company.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .clickable { /*TODO*/ }
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Health Care Planning",
                        fontSize = 20.sp,
                        color = Purple_200,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.healthcare), // Replace with your image resource
                        contentDescription = "Card 2 Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(230.dp)
                            .padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Evaluate the need for long-term care insurance to cover expenses of Medicare.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "Connect with a financial advisor and optimize you financial decisions.Get one on one mentorship now with our FinPro mentorship." ,
                        fontSize = 18.sp,
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(Purple_200),
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "Help", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetireScreen()
}