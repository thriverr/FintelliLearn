package com.example.myscreen.Firebase

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthInstance {
    val instance: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
}