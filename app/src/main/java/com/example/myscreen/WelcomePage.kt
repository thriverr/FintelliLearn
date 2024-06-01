package com.example.myscreen

import androidx.compose.runtime.Composable

@Composable
fun WelcomePage(userId: String?) {

        NavDrawer(userId = userId) // Assuming this displays the navigation drawer
    }
