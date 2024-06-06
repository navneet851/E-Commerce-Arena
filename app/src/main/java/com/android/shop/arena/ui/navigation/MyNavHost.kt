package com.android.shop.arena.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.shop.arena.ui.screens.CartScreen
import com.android.shop.arena.ui.screens.HomeScreen
import com.android.shop.arena.ui.screens.ProfileScreen
import com.android.shop.arena.ui.screens.StoreScreen
import kotlinx.serialization.Serializable



@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home"){
            composable("home") {
                HomeScreen()
            }
            composable("store") {
                StoreScreen()
            }
            composable("cart") {
                CartScreen()
            }
            composable("profile") {
                ProfileScreen()
            }
    }
}





