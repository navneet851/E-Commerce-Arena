package com.android.shop.arena.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.android.shop.arena.data.pref.DataStoreManager
import com.android.shop.arena.ui.screens.AddressScreen
import com.android.shop.arena.ui.screens.CartScreen
import com.android.shop.arena.ui.screens.HomeScreen
import com.android.shop.arena.ui.screens.LoginScreen
import com.android.shop.arena.ui.screens.MyOrdersScreen
import com.android.shop.arena.ui.screens.NotificationScreen
import com.android.shop.arena.ui.screens.OrderScreen
import com.android.shop.arena.ui.screens.ProductScreen
import com.android.shop.arena.ui.screens.ProfileScreen
import com.android.shop.arena.ui.screens.RegisterScreen
import com.android.shop.arena.ui.screens.StoreScreen
import kotlinx.serialization.Serializable


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
                StoreScreen(paddingValues, navController)
            }
            composable("cart") {
                bars.value = true
                CartScreen(paddingValues, navController)
            }
            composable<Product> {
                val product : Product = it.toRoute()
                bars.value = false
                ProductScreen(product.id, navController)
            }
            composable("profile") {
                bars.value = true
                ProfileScreen(navController, dataStore, paddingValues)
            }
            composable("address") {
                bars.value = false
                AddressScreen(navController, paddingValues)
            }
            composable<Order> {
                val order : Order = it.toRoute()
                bars.value = false
                OrderScreen(order.time, navController)
            }
            composable("myOrders"){
                bars.value = false
                MyOrdersScreen(navController)
            }
            composable("notification"){
                bars.value = false
                NotificationScreen(navController)
            }

    }


}

@Serializable
data class Product(val id : Int)

@Serializable
data class Order(val time : String)




