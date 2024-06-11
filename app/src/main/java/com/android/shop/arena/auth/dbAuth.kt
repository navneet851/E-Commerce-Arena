package com.android.shop.arena.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.firestore.FirebaseFirestore

fun checkPhoneNumberInDatabase(phoneNumber: String, isPresent : (Boolean) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("users")
        .document(phoneNumber)
        .get()
        .addOnSuccessListener { document ->
            if (document != null && document.exists()) {
                // The phone number is present in the database
                Log.d("Firestore", "Phone number found in database")
                isPresent(true)
            } else {
                // The phone number is not present in the database
                Log.d("Firestore", "Phone number not found in database")
                isPresent(false)
            }
        }
        .addOnFailureListener { e ->
            // There was an error checking the database
            Log.w("Firestore", "Error checking database for phone number", e)
        }
}


//Login
fun checkUserCredentialsInDatabase(phoneNumber: String, password: String, onSuccess : (Boolean) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("users")
        .document(phoneNumber)
        .get()
        .addOnSuccessListener { document ->
            if (document != null && document.exists()) {
                // The phone number is present in the database
                val docPassword = document.getString("password")
                if (docPassword != null) {
                    if (docPassword == password) {
                        // The password matches
                        Log.d("Firestore", "User credentials are correct")
                        onSuccess(true)
                    } else {
                        // The password does not match
                        Log.d("Firestore", "Incorrect password")
                        onSuccess(false)
                    }
                }
            } else {
                // The phone number is not present in the database

                Log.d("Firestore", "Phone number not found in database")
            }
        }
        .addOnFailureListener { e ->
            // There was an error checking the database
            Log.w("Firestore", "Error checking database for user credentials", e)
        }
}