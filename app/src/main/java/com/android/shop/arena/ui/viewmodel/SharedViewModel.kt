package com.android.shop.arena.ui.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.shop.arena.api.Api
import com.android.shop.arena.data.entity.Address
import com.android.shop.arena.data.entity.Cart
import com.android.shop.arena.data.entity.Game
import com.android.shop.arena.data.entity.Transaction
import com.android.shop.arena.data.entity.User
import com.android.shop.arena.data.pref.DataStoreManager
import com.bumptech.glide.Glide.init
import com.google.android.play.integrity.internal.f
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Locale.filter

class SharedViewModel(application: Application) : AndroidViewModel(application){
    private val dataStoreManager = DataStoreManager(application)


    private val _games : MutableStateFlow<List<Game>> = MutableStateFlow(emptyList())
    val games : StateFlow<List<Game>> = _games

    private val _cart : MutableStateFlow<List<Cart>> = MutableStateFlow(emptyList())
    val cartItems : StateFlow<List<Cart>> = _cart

    private val _addresses : MutableStateFlow<List<Address>> = MutableStateFlow(emptyList())
    val address : StateFlow<List<Address>> = _addresses

    private val _transactions : MutableStateFlow<List<Transaction>> = MutableStateFlow(emptyList())
    val transactions : StateFlow<List<Transaction>> = _transactions

    val totalItems : MutableState<Int> = mutableStateOf(0)

    val userId : MutableState<String> = mutableStateOf("")

    private var api : Api? = null

    init {
        getUser()
    }

    fun getCurrentDateTimeFormatted(): String {
        val dateFormat = SimpleDateFormat("H:mm d-M-yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }


    fun calculateTotalAmount(cartItemsDetails: List<Game>, cartItems: List<Cart>): Int {
        var totalAmount = 0
        for (i in cartItemsDetails.indices){
            val amount = cartItemsDetails[i].discountPrice.replace(",", "").toInt()
            totalAmount += amount * cartItems[i].quantity
            totalItems.value += cartItems[i].quantity
        }
        return totalAmount
    }


    private fun getUser() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreManager.uidFlow.collect{ uid ->
            userId.value = uid?: ""
            api = Api(userId.value)
            getGames()
            cartItems()
            getAddresses()
            fetchTransactions()
        }
    }

    private fun getGames() = viewModelScope.launch(Dispatchers.IO) {
       api?.getGames()?.collect{
           _games.value = it
       }
    }

    private fun cartItems() = viewModelScope.launch(Dispatchers.IO) {
        api?.cartItems()?.collect{
            _cart.value = it
        }
    }

    private fun getAddresses() = viewModelScope.launch(Dispatchers.IO) {
        api?.fetchAddresses()?.collect{
            _addresses.value = it
        }
    }

    private fun fetchTransactions() = viewModelScope.launch(Dispatchers.IO) {
        api?.fetchTransactions()?.collect{
            _transactions.value = it
        }
    }

    fun refreshCartItems() = viewModelScope.launch(Dispatchers.IO) {
        api?.cartItems()?.collect{
            _cart.value = it
        }
    }

    fun refreshAddresses() = viewModelScope.launch(Dispatchers.IO) {
        api?.fetchAddresses()?.collect{
            _addresses.value = it
        }
    }



}