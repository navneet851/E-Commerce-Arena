package com.android.shop.arena.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.shop.arena.R

@Preview
@Composable
fun BottomBar() {

    val navItems = listOf(
        NavItem("home", R.drawable.home_, "Home"),
        NavItem("store", R.drawable.store, "Store"),
        NavItem("cart", R.drawable.cart, "Cart"),
        NavItem("profile", R.drawable.profile, "Profile"),
    )

    NavigationBar(
        Modifier.fillMaxWidth()
    ) {
        navItems.forEach { item ->
            NavigationBarItem(
                selected = true,
                onClick = { /*TODO*/ },
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