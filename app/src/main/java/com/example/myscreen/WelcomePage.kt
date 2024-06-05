package com.example.myscreen

import androidx.compose.runtime.Composable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun WelcomePage(auth: FirebaseAuth, db: FirebaseFirestore) {

        NavDrawer(
            auth=auth ,
            db = db
        ) // Assuming this displays the navigation drawer
    }
