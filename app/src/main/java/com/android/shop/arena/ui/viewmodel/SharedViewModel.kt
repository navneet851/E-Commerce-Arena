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
import com.android.shop.arena.data.entity.Cart
import com.android.shop.arena.data.entity.Game
import com.android.shop.arena.data.entity.User
import com.android.shop.arena.data.pref.DataStoreManager
import com.bumptech.glide.Glide.init
import com.google.android.play.integrity.internal.f
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application){
    private val dataStoreManager = DataStoreManager(application)


    private val _games : MutableStateFlow<List<Game>> = MutableStateFlow(emptyList())
    val games : StateFlow<List<Game>> = _games

    private val _cart : MutableStateFlow<List<Cart>> = MutableStateFlow(emptyList())
    val cartItems : StateFlow<List<Cart>> = _cart

    val userId : MutableState<String> = mutableStateOf("")

    private var api : Api? = null

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreManager.uidFlow.collect{ uid ->
            userId.value = uid?: ""
            api = Api(userId.value)
            getGames()
            cartItems()
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

}