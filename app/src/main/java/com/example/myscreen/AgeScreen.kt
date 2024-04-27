package com.example.myscreen

import ImpOfSavingArticle
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArtTrack
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myscreen.ui.theme.BgBlueColor
import com.example.myscreen.ui.theme.Purple_200
import kotlinx.coroutines.launch

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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(){
    val navigationController= rememberNavController()
    val coroutineScope= rememberCoroutineScope()
    val drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(modifier = Modifier
                    .background(Purple_200)
                    .fillMaxWidth()
                    .height(100.dp),
                    contentAlignment = Alignment.Center
                ){

                    Text(text = "Dashboard", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                        color = Color.White)
                }
                Divider()
                NavigationDrawerItem(label = { Text(text = "Home", color = Color.Black) },
                    selected =false ,
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription ="Home" ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Routes.AgeScreen){
                            popUpTo(0)

                        }
                    })
                NavigationDrawerItem(label = { Text(text = "News", color = Color.Black) },
                    selected =false ,
                    icon = { Icon(imageVector = Icons.Default.Newspaper, contentDescription ="News" ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Routes.NewsScreen){
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Budget Tracker", color = Color.Black) },
                    selected =false ,
                    icon = { Icon(imageVector = Icons.Default.ArtTrack, contentDescription ="Budget Tracker" ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Routes.BudgetTracker){
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Community Forum", color = Color.Black) },
                    selected =false ,
                    icon = { Icon(imageVector = Icons.Default.Forum, contentDescription =" Community Forum" ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Routes.CommunityForum){
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Finanial Calculator", color = Color.Black) },
                    selected =false ,
                    icon = { Icon(imageVector = Icons.Default.Calculate, contentDescription ="Financial Calculator" ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Routes.CalculationPage){
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Financial Health Assessment Bot", color = Color.Black) },
                    selected =false ,
                    icon = { Icon(imageVector = Icons.Default.ChatBubbleOutline, contentDescription ="Financial Health Assessment Bot" ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Routes.SignInPage){
                            popUpTo(0)
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Logout", color = Color.Black) },
                    selected =false ,
                    icon = { Icon(imageVector = Icons.Default.Logout, contentDescription ="Logout" ) },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Routes.SignInPage){
                            popUpTo(0)
                        }
                    })

            }
        })
    {
        Scaffold(

            topBar = {
                val coroutineScope= rememberCoroutineScope()
                TopAppBar(title = {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(BgBlueColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Fintelli Learn", fontFamily = FontFamily.Serif, fontSize = 20.sp
                        , fontWeight = FontWeight.Bold
                        )
                    }
                }
                    , navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {  drawerState.open()}
                        }, modifier = Modifier
                            .background(Purple_200)
                            .padding(8.dp) // Add padding around the icon for a better visual effect
                        ) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Icon",
                                tint = Color.White)
                        }

                    })
            }
        ) {

            NavHost(navController = navigationController, startDestination =Routes.AgeScreen  ){
                composable(Routes.AgeScreen){
                    AgeScreen(navController = navigationController)
                }
                composable(Routes.CommunityForum){
                    CommunityForum()
                }
                composable(Routes.CalculationPage){
                    CalculationPage(navController =navigationController )
                }
                composable(Routes.BudgetTracker){
                   BudgetTracker()
                }
                composable(Routes.AdultScreen){
                    AdultScreen(navController =navigationController)
                }
                composable(Routes.CompoundInterestCalculator){
                    CompoundInterestCalculator()
                }
                composable(Routes.Bot){
                    Bot()
                }
                composable(Routes.quiz){
                    VideoPlay()
                }
                composable(Routes.ImpOfSavingArticle){
                    ImpOfSavingArticle()
                }
                composable(Routes.CreditPage){
                   CreditPage()
                }
                composable(Routes.DefaultDestination){
                    DefaultDestination()
                }
                composable(Routes.SignInPage){
                    SignInPage(navController =navigationController )
                }
                composable(Routes.KidsScreen){
                    KidsScreen(navController =navigationController)
                }
                composable(Routes.LearningPage){
                    val data = listOf(
                        Triple("Importance of Saving", "Learn About Saving",  R.drawable.piggy),
                        Triple("What is credit?", "Credit", R.drawable.credit),
                        Triple("What is debit?", "Debit",R.drawable.debit ),
                        Triple("Credit vs Debit", "Difference", R.drawable.creditvsdebit),
                        Triple("Importance of Saving", "Learn About Saving",  R.drawable.piggy),
                        Triple("What is credit?", "Credit", R.drawable.credit),
                        Triple("What is debit?", "Debit",R.drawable.debit ),
                        Triple("Credit vs Debit", "Difference", R.drawable.creditvsdebit)
                        // Add more rows as needed
                    )
                    Table(data = data,navController =navigationController)
                }
                composable(Routes.NewsScreen){
                    val newsViewModel = NewsViewModel()

                    // Pass NewsViewModel to NewsScreen
                    NewsScreen(newsViewModel = newsViewModel)
                }
            }

        }

    }

}
