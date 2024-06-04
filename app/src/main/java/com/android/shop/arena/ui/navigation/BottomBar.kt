package com.android.shop.arena.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.shop.arena.R


@Composable
fun BottomBar(navController: NavHostController) {

    val navItems = listOf(
        NavItem(Home.toString(), R.drawable.home_, "Home"),
        NavItem(Store.toString(), R.drawable.store, "Store"),
        NavItem(Cart.toString(), R.drawable.cart, "Cart"),
        NavItem(Profile.toString(), R.drawable.profile, "Profile"),
    )

    NavigationBar(
        Modifier.fillMaxWidth()
    ) {

        val navStack by navController.currentBackStackEntryAsState()
        val currentRoute = navStack?.destination?.route
        Log.d("BottomBar", "currentRoute: ${currentRoute.toString()}")

        navItems.forEach { item ->

            NavigationBarItem(

                selected = currentRoute == item.route,
                onClick = {
                          navController.navigate(item.route)
                },
                icon = {
                       Icon(
                           painter = painterResource(id = item.icon),
                           contentDescription = item.title,
                           modifier = Modifier.size(20.dp)
                       )
                },
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = true
            )
        }
    }

}

class NavItem(var route: String, var icon: Int, var title: String)