package com.android.shop.arena.api

import android.util.Log
import com.android.shop.arena.data.entity.Address
import com.android.shop.arena.data.entity.Cart
import com.android.shop.arena.data.entity.Game
import com.android.shop.arena.data.entity.Transaction
import com.android.shop.arena.data.entity.User
import com.android.shop.arena.data.pref.DataStoreManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            emit(emptyList())
            val snapshot = firestore.collection("cart")
                .whereEqualTo("uid", uid)
                .get().await()
            val cartItems = snapshot.toObjects(Cart::class.java)
            emit(cartItems)
        }
    }

    suspend fun fetchAddresses() : Flow<List<Address>> {
        return flow {
            emit(emptyList())
            val snapshot = firestore.collection("addresses")
                .whereEqualTo("uid", uid)
                .get().await()
            val addresses = snapshot.toObjects(Address::class.java)
            emit(addresses)
        }
    }

    suspend fun fetchTransactions() : Flow<List<Transaction>> {
        return flow{
            emit(emptyList())
            val snapshot = firestore.collection("transactions")
                .whereEqualTo("uid", uid)
                .get().await()
            val transactions = snapshot.documents.map { document ->
                document.toObject(Transaction::class.java)?.apply {
                    transactionId = document.id // Set the document ID here
                }
            }.filterNotNull()
            emit(transactions)
        }
    }

}