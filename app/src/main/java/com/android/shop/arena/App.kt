package com.android.shop.arena

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.android.shop.arena.ui.navigation.BottomBar
import com.android.shop.arena.ui.navigation.MyNavHost
import com.android.shop.arena.ui.navigation.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    Scaffold (
        containerColor = Color.White,
        topBar = {
                 TopBar()
        },
        bottomBar = {
            BottomBar(navController)
        }
    ){
        Box(modifier = Modifier.padding(it).fillMaxSize()){
            MyNavHost(navController = navController)
        }

    }
}