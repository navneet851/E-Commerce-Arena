package com.android.shop.arena.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.shop.arena.api.Api
import com.android.shop.arena.data.entity.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel(){
    private val _games : MutableStateFlow<List<Game>> = MutableStateFlow(emptyList())
    val games : StateFlow<List<Game>> = _games

    private val api = Api()

    init {
        getGames()
    }

    private fun getGames() = viewModelScope.launch {
       api.getGames().collect{
           _games.value = it
       }
    }
}