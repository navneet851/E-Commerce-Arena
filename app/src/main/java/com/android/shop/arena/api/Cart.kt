package com.android.shop.arena.api

import android.util.Log
import com.android.shop.arena.data.entity.Address
import com.android.shop.arena.data.entity.Cart
import com.android.shop.arena.data.entity.Transaction
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
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

fun saveAddressToFirestore(address: Address, onClick : () -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("addresses").add(address)
        .addOnSuccessListener { documentReference ->
            Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
            onClick()
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error adding document", e)
        }
}

fun deleteAddressByFlat(flat: String, uid: String, onClick: () -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("addresses")
        .whereEqualTo("uid", uid)
        .whereEqualTo("flat", flat)
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                db.collection("addresses").document(document.id).delete()
            }
            onClick()
        }
}

fun addTransaction(transaction: Transaction, onSuccess: () -> Unit){
    FirebaseFirestore.getInstance().collection("transactions")
        .add(transaction)

}

fun addTransactionToFirestore(transaction: Transaction, onSuccess: () -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("transactions")
        .add(transaction)
        .addOnSuccessListener {
            onSuccess()
        }
        .addOnFailureListener { e ->
            Log.d("Firestore", "Error adding document", e)
        }
}