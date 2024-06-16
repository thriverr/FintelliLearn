package com.example.myscreen.navigation

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myscreen.AIBot.Bot
import com.example.myscreen.AboutProfile
import com.example.myscreen.AddPostScreen
import com.example.myscreen.BudgetTracker
import com.example.myscreen.CommunityForum
import com.example.myscreen.Firebase.SignInPage
import com.example.myscreen.R
import com.example.myscreen.articles.CreditPage
import com.example.myscreen.articles.DefaultDestination
import com.example.myscreen.articles.IntroBudgeting
import com.example.myscreen.articles.SavingsAccount
import com.example.myscreen.articles.TypesOfSavings
import com.example.myscreen.calculators.CalculationPage
import com.example.myscreen.calculators.CompoundInterestCalculator
import com.example.myscreen.module.Table
import com.example.myscreen.module.TableBudget
import com.example.myscreen.module.VideoPlay
import com.example.myscreen.news.NewsScreen
import com.example.myscreen.news.NewsViewModel
import com.example.myscreen.screens.AdultScreen
import com.example.myscreen.screens.AgeScreen
import com.example.myscreen.screens.KidsScreen
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

                    Text(text = "Dashboard", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                        color = Color.White)
                }
                Divider()
                NavigationDrawerItem(label = { Text(text = "Home", color = Color.Black,
                    fontWeight = FontWeight.Bold, fontSize = 16.sp) },
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
                NavigationDrawerItem(label = { Text("Your Profile", color = Color.Black,
                    fontWeight = FontWeight.Bold, fontSize = 16.sp) },
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
                NavigationDrawerItem(label = { Text(text = "News", color = Color.Black,
                    fontWeight = FontWeight.Bold, fontSize = 16.sp) },
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
                NavigationDrawerItem(label = { Text(text = "Budget Tracker", color = Color.Black,
                    fontWeight = FontWeight.Bold, fontSize = 16.sp) },
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
                NavigationDrawerItem(label = { Text(text = "Community Forum", color = Color.Black,
                    fontWeight = FontWeight.Bold, fontSize = 16.sp) },
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
                NavigationDrawerItem(label = { Text(text = "Financial Calculator", color = Color.Black,
                    fontWeight = FontWeight.Bold, fontSize = 16.sp) },
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
                NavigationDrawerItem(label = { Text(text = "Financial Health Assessment Bot", color = Color.Black,
                    fontWeight = FontWeight.Bold, fontSize = 16.sp) },
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
                NavigationDrawerItem(label = { Text(text = "Logout", color = Color.Black,
                    fontWeight = FontWeight.Bold, fontSize = 16.sp) },
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
                            .background(Purple_200),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Finshaala!", fontFamily = FontFamily.Default, fontSize = 26.sp
                            , fontWeight = FontWeight.Bold, color = Color.White
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

            NavHost(navController = navigationController, startDestination = Routes.AgeScreen  ){
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
                    BudgetTracker(auth=auth)
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
                composable(
                    Routes.AboutProfile
                    ) {
                    AboutProfile( currentUser = auth.currentUser!!)
                }
                composable(Routes.quiz){
                    VideoPlay()
                }
                composable(Routes.ImpOfSavingArticle){
                    ImpOfSavingArticle()
                }
                composable(Routes.IntroBudgeting){
                    IntroBudgeting()
                }
                composable(Routes.CreditPage){
                    CreditPage()
                }
                composable(Routes.SavingsAccount){
                    SavingsAccount()
                }
                composable(Routes.TypesOfSavings){
                    TypesOfSavings()
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
                    val easyData = listOf(
                        Triple("Introduction to Saving", "The power of Saving", R.drawable.piggy),
                        Triple("Setting Aside Allowance or Pocket Money for Savings", "Mastering art of Saving",
                            R.drawable.allowance
                        ),
                        Triple("Saving for Short-Term Goals", "Saving goals", R.drawable.shortgoal),

                        Triple("Identifying Wants vs. Needs", "Needs or Wants?",
                            R.drawable.needswants
                        ),
                        // Add more rows as needed
                    )

                    val mediumData = listOf(
                        Triple("Opening a Savings Account", "Savings Account",
                            R.drawable.savingsac),
                        Triple("Setting and Tracking Savings", "Track Saving Goals",
                            R.drawable.savetrack
                        ),
                        Triple("Learning About Interest and How It Grows Savings", "Explore Interests"
                            , R.drawable.interest),
                        Triple("Exploring basic financial terms", "Financial Terms",
                            R.drawable.financeterms
                        )
                    )

                    val hardData = listOf(

                        Triple("Exploring Different Types Of Savings Accounts", "Exploring Savings Accounts",
                            R.drawable.typesofsaving
                        ),
                        // Add more rows as needed
                        Triple("Creating a Long-Term Savings Plan", "Long term goals"
                            , R.drawable.longsave),
                        Triple("Introduction to Investing for Teens", "Explore Investing",
                            R.drawable.investing
                        ),
                        Triple("Learning About Credit and Debt Management", "Credit & Debt management"
                            , R.drawable.debtmanage),

                    )

                    val levels = listOf(
                        "Beginner" to easyData,
                        "Intermediate" to mediumData,
                        "Advance" to hardData
                    )

                    Table(levels = levels, navController = navigationController)


                }

                composable(Routes.BudgetLearningPage){
                    val easyData = listOf(
                        Triple("Introduction to Budgeting", "What is Budget?"
                            , R.drawable.introbudget),
                        Triple("Tracking Income and Expenses",
                            "Learn About Tracking",
                            R.drawable.trackingfinance
                        ),
                        Triple("Creating a Basic Monthly Budget", "Create Your Budget",
                            R.drawable.basicbudget),

                        Triple("Identifying and Cutting Unnecessary Expenses",
                            "Remove Unnecessary Expenses",
                            R.drawable.cuttingexpenses
                        ),
                        // Add more rows as needed
                    )

                    val mediumData = listOf(
                        Triple("Developing a Comprehensive Budgeting Plan",
                            "Have A Budgeting Plan!",
                            R.drawable.budgetingplan),
                        Triple("Understanding Different Budgeting Methods",
                            "Budgeting Methods",
                            R.drawable.budgetingrule
                        ),
                        Triple("Budgeting for Irregular Expenses",
                            "Handle Irregular Expenses"
                            , R.drawable.irregularexpense),
                        Triple("Emergency Fund Planning and Savings",
                            "Emergency Funds",
                            R.drawable.emergency
                        )
                    )

                    val hardData = listOf(

                        Triple("Advanced Expense Forecasting and Planning",
                            "Planning Future Expenses",
                            R.drawable.forecasting
                        ),
                        // Add more rows as needed
                        Triple("Incorporating Debt Repayment Strategies into the Budget",
                            "Strategies to Repay Debt"
                            , R.drawable.strategiesbudgeting),
                        Triple("Budgeting for Variable Income or Self-Employment",
                            "Know About Self Employment",
                            R.drawable.selfemployment
                        ),
                        Triple("Tax Planning and Budgeting for Tax Liabilities",
                            "Reduce Tax Liabilities"
                            , R.drawable.taxplanning),

                        )

                    val levels = listOf(
                        "Beginner" to easyData,
                        "Intermediate" to mediumData,
                        "Advance" to hardData
                    )

                    TableBudget(levels = levels, navController = navigationController)


                }
                composable(Routes.NewsScreen){
                    val newsViewModel = NewsViewModel()
                    val context = LocalContext.current
                    // Pass NewsViewModel to NewsScreen
                    NewsScreen(newsViewModel = newsViewModel,context)
                }
            }

        }

    }

}

