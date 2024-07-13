package com.android.shop.arena.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.shop.arena.R
import com.android.shop.arena.ui.theme.CardColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}

class NavItem(var route: String, var unSelectedIcon: Int, var selectedIcon: Int, var title: String)

@Composable
fun BottomBar(navController: NavHostController, visible: MutableState<Boolean>) {

    val navItems = listOf(
        NavItem("home", R.drawable.home, R.drawable.home_fill, "Home"),
        NavItem("store", R.drawable.store, R.drawable.store_fill, "Store"),
        NavItem("cart", R.drawable.cart, R.drawable.cart_fill, "Cart"),
        NavItem("profile", R.drawable.profile, R.drawable.profile_fill, "Profile"),
    )

    AnimatedVisibility(
        visible = visible.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            NavigationBar(
                Modifier
                    .fillMaxWidth().height(90.dp),
                tonalElevation = 6.dp,
                containerColor = CardColor,

                ) {

                val navStack by navController.currentBackStackEntryAsState()
                val currentRoute = navStack?.destination?.route

                navItems.forEach { item ->
                    NavigationBarItem(

                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route){
                                navController.navigate(item.route)
                            }

                        },
                        icon = {
                            Icon(
                                painter = painterResource(id =
                                if (currentRoute == item.route) item.selectedIcon else item.unSelectedIcon),
                                contentDescription = item.title,
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        interactionSource = NoRippleInteractionSource(),
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = Color.Black,
                            unselectedIconColor = Color.Black,
                            unselectedTextColor = Color.Black,
                            indicatorColor = Color.Transparent
                        ),
                        label = {
                            Text(text = item.title, fontSize = 12.sp)
                        },
                        alwaysShowLabel = true
                    )
                }
            }
        }
    )


}

