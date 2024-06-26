package com.android.shop.arena.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.shop.arena.data.pref.DataStoreManager
import com.android.shop.arena.ui.screens.CartScreen
import com.android.shop.arena.ui.screens.HomeScreen
import com.android.shop.arena.ui.screens.LoginScreen
import com.android.shop.arena.ui.screens.ProductScreen
import com.android.shop.arena.ui.screens.ProfileScreen
import com.android.shop.arena.ui.screens.RegisterScreen
import com.android.shop.arena.ui.screens.StoreScreen


@Composable
fun MyNavHost(
    navController: NavHostController,
    dataStore: DataStoreManager,
    bars: MutableState<Boolean>,
    paddingValues: PaddingValues
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    bars.value = currentRoute in listOf("home", "store", "cart", "profile")
    
    NavHost(navController = navController, startDestination = "home"){
            composable("register") {
                bars.value = false
                RegisterScreen(navController, dataStore)
            }
            composable("login") {
                bars.value = false
                LoginScreen(navController, dataStore)
            }
            composable("home") {
                bars.value = true
                HomeScreen(paddingValues, navController)
            }
            composable("store") {
                bars.value = true
                StoreScreen(paddingValues)
            }
            composable("cart") {
                bars.value = true
                CartScreen(paddingValues)
            }
            composable("product") {
                bars.value = false
                ProductScreen()
            }
            composable("profile") {
                bars.value = true
                ProfileScreen(navController, dataStore, paddingValues)
            }

    }
}





