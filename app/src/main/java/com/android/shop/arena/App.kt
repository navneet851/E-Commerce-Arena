package com.android.shop.arena

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.shop.arena.data.pref.DataStoreManager
import com.android.shop.arena.ui.navigation.BottomBar
import com.android.shop.arena.ui.navigation.MyNavHost
import com.android.shop.arena.ui.navigation.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val dataStore = DataStoreManager(LocalContext.current)
    val bars = rememberSaveable {
        mutableStateOf(true)
    }
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    when (currentRoute) {
        "home" -> {
            bars.value = true
        }
        "cart" -> {
            bars.value = true
        }
        "shop" -> {
            bars.value = true
        }
        "profile" -> {
            bars.value = true
        }
        "login" -> {
            bars.value = false
        }
        "register" -> {
            bars.value = false
        }
        "notification" -> {
            bars.value = true
        }
    }

    Scaffold (
        containerColor = Color.White,
        topBar = {
            TopBar(bars)
        },
        bottomBar = {
            BottomBar(navController = navController, bars)
        }
    ){
        Box(modifier = Modifier
            //.padding(it)
            .fillMaxSize()){
            MyNavHost(navController = navController, dataStore, bars, it)
        }

    }
}