package com.android.shop.arena.api

import android.util.Log
import com.android.shop.arena.data.entity.Address
import com.android.shop.arena.data.entity.Cart
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

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

suspend fun updateCartItemQuantity(itemId: Int, uid: String, newQuantity: Int) {
    val cartItemRef = FirebaseFirestore.getInstance().collection("cart")
        .whereEqualTo("id", itemId)
        .whereEqualTo("uid", uid)
        .limit(1)
        .get()
        .await()
        .documents
        .firstOrNull()

    cartItemRef?.reference?.update("quantity", newQuantity)
}

suspend fun removeCartItem(itemId: Int, uid: String) {
    val cartItemRef = FirebaseFirestore.getInstance().collection("cart")
        .whereEqualTo("id", itemId)
        .whereEqualTo("uid", uid)
        .limit(1)
        .get()
        .await()
        .documents
        .firstOrNull()

    cartItemRef?.reference?.delete()
}

fun saveAddressToFirestore(address: Address) {
    val db = FirebaseFirestore.getInstance()
    db.collection("addresses").add(address)
        .addOnSuccessListener { documentReference ->
            Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error adding document", e)
        }
}