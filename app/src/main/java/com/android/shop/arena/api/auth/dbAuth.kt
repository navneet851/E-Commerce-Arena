package com.android.shop.arena.api.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.android.shop.arena.data.entity.User
import com.android.shop.arena.data.pref.DataStoreManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

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
fun checkUserCredentialsInDatabase(phoneNumber: String, password: String, dataStore: DataStoreManager, onSuccess : (Boolean) -> Unit) {
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
                        CoroutineScope(Dispatchers.Main).launch {
                            val token = document.getString("uid")!!
                            Log.d("Firestore", "Token: $token")
                            dataStore.saveUID(token)
                        }
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


fun addUser(user : User, onSuccess : () -> Unit){
    val db = FirebaseFirestore.getInstance()
    db.collection("users")
        .document(user.phone)
        .set(user)
        .addOnSuccessListener {
            Log.d("Firestore", "User successfully written!")
            onSuccess()
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error writing user", e)
        }
}

suspend fun fetchUserByUID(uid: String): QuerySnapshot? {
    val db = FirebaseFirestore.getInstance()

    return try {
        db.collection("users")
            .whereEqualTo("uid", uid)
            .get()
            .await()
    } catch (e: Exception) {
        Log.d("fetchUserByUID", "Error getting documents: ", e)
        null
    }
}