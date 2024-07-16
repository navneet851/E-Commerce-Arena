package com.android.shop.arena.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.shop.arena.R
import com.android.shop.arena.ui.theme.CardColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(visible: MutableState<Boolean>, navController: NavHostController) {

    AnimatedVisibility(
        visible = visible.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = "")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CardColor,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                ),
                navigationIcon = {
                    Image(
                        modifier = Modifier
                            .padding(12.dp, 0.dp)
                            .size(43.dp)
                            .clip(RoundedCornerShape(10.dp))
                        ,
                        painter = painterResource(id = R.drawable.arena_logo),
                        contentScale = ContentScale.Fit,
                        contentDescription = "logo"
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("notification")
                    }) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.notification),
                            contentDescription = "search")
                    }
                }
            )
        }
    )

}