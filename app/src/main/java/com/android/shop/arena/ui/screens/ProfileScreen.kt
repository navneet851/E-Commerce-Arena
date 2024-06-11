package com.android.shop.arena.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.shop.arena.R
import com.android.shop.arena.ui.components.ProfileMenuItem
import com.android.shop.arena.ui.theme.CardColor
import com.android.shop.arena.ui.theme.InputColor

@Preview
@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(10.dp)
                .clip(shape = RoundedCornerShape(10))
                .background(InputColor)
        ){
            Image(
                modifier = Modifier
                    .size(150.dp)
                    .clip(shape = RoundedCornerShape(50)),
                painter = painterResource(id = R.drawable.arena_logo),
                contentDescription = "profile image")
        }

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp, 0.dp)
                .clip(shape = RoundedCornerShape(5))
                .background(InputColor)
                .padding(5.dp, 10.dp)
        ){
            ProfileMenuItem(icon = R.drawable.baseline_list_alt_24, title = "My Details")
            ProfileMenuItem(icon = R.drawable.baseline_wb_shade_24, title = "My Orders")
        }

    }
}