package com.android.shop.arena.api

import android.util.Log
import com.android.shop.arena.data.entity.Cart
import com.android.shop.arena.data.entity.Game
import com.android.shop.arena.data.entity.User
import com.android.shop.arena.data.pref.DataStoreManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class Api(private val uid : String) {
    private val firestore : FirebaseFirestore = Firebase.firestore

    suspend fun getGames() : Flow<List<Game>> {
        return flow{
            emit(emptyList<Game>())
            val snapshot = firestore.collection("games")
                .get().await()
            val games = snapshot.toObjects(Game::class.java)
            emit(games)
        }
    }

    suspend fun cartItems() : Flow<List<Cart>> {
        return flow{
            emit(emptyList<Cart>())
            val snapshot = firestore.collection("cart")
                .whereEqualTo("uid", uid)
                .get().await()
            val cartItems = snapshot.toObjects(Cart::class.java)
            emit(cartItems)
        }
    }

}