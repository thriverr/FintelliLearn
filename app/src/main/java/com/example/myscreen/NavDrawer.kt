package com.example.myscreen

import ImpOfSavingArticle
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArtTrack
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myscreen.AIBot.Bot
import com.example.myscreen.ui.theme.BgBlueColor
import com.example.myscreen.ui.theme.Purple_200
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(auth: FirebaseAuth, db: FirebaseFirestore){
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
                NavigationDrawerItem(label = { Text("Your Profile", color = Color.Black) },
                    selected = false,
                    icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Your Profile") },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigationController.navigate(Routes.AboutProfile) {
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
                        navigationController.navigate(Routes.Bot){
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
                        Text(text = "Finshaala!", fontFamily = FontFamily.Serif, fontSize = 20.sp
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
                composable(
                    route = Routes.CommunityForum)
                    {
                        CommunityForum( currentUser = auth.currentUser!!, db = db,navController=navigationController)
                    }

                composable("addPost") { AddPostScreen(navController=navigationController,db=db,
                    currentUser = auth.currentUser!!) }

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
                composable(Routes.AboutProfile
                    ) {
                    AboutProfile( currentUser = auth.currentUser!!)
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
                        Triple("Importance of Saving", "The power of Saving",  R.drawable.piggy),
                        Triple("How do you Save?", "Mastering art of Saving", R.drawable.howdoyousave),
                        Triple("Setting Savings Goals", "Saving goals",R.drawable.settinggoals ),
                        Triple("Creating A budget with Savings", "Creating budget with Savings", R.drawable.budgetsaving),
                        Triple("Emergency Fund", "Emergency fund",  R.drawable.emergencyfund),
                        Triple("Types Of Savings Accounts", "Exploring different Options", R.drawable.typesofsaving),
                        Triple("Savings on Everyday Expenses", "Strategies for it",R.drawable.savingeverydayexpenses ),
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

