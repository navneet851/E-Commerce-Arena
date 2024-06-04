package com.android.shop.arena

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.android.shop.arena.ui.navigation.BottomBar
import com.android.shop.arena.ui.navigation.MyNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    Scaffold (
        containerColor = Color.White,
        bottomBar = {
            BottomBar(navController)
        }
    ){


        MyNavHost(navController = navController)
    }
}