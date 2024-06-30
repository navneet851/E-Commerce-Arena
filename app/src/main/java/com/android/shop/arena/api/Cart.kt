package com.android.shop.arena.api

import android.util.Log
import com.android.shop.arena.data.entity.Cart
import com.google.firebase.firestore.FirebaseFirestore

fun addToCart(cartItem : Cart, onSuccess : () -> Unit){
    FirebaseFirestore.getInstance().collection("cart")
        .add(cartItem)
        .addOnSuccessListener {
            Log.d("Firestore", "Product successfully added to cart!")
            onSuccess()
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error adding product to cart", e)
        }
}