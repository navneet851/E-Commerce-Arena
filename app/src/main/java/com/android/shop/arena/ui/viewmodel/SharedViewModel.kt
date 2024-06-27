package com.android.shop.arena.ui.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.shop.arena.api.Api
import com.android.shop.arena.data.entity.Game
import com.android.shop.arena.data.entity.User
import com.android.shop.arena.data.pref.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SharedViewModel() : ViewModel(){
    private val _games : MutableStateFlow<List<Game>> = MutableStateFlow(emptyList())
    val games : StateFlow<List<Game>> = _games


    private val api = Api()

    init {
        getGames()
    }

    private fun getGames() = viewModelScope.launch(Dispatchers.IO) {
       api.getGames().collect{
           _games.value = it
       }
    }
//    fun fetchUserByUID(uid: String): Flow<User> {
//        return flow {
//            fetchUserByUID(uid).collect{
//                emit(it)
//            }
//
//        }
//    }

}