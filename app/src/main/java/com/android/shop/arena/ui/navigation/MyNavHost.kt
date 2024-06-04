package com.android.shop.arena.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable



@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Home){
            composable<Home> {  }
    }
}


@Serializable
object Home



