package com.android.shop.arena.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.android.shop.arena.R
import com.android.shop.arena.data.entity.OrderState
import com.android.shop.arena.ui.components.OrderProgress

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.White,

        topBar = {
            Column {
                TopAppBar(
                    modifier = Modifier.background(Color.White).padding(10.dp, 0.dp),
                    title = {
                        Text(text = "")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        navigationIconContentColor = Color.Black,
                    ),
                    navigationIcon = {
                        Icon(
                            modifier = Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                navController.navigateUp()
                            },
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "back"
                        )
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    val state = listOf<OrderState>(OrderState(Color(0xFF00C41E), "Bag"), OrderState(Color(0xFF00C41E), text = "Address"), OrderState(text = "Payment"))
                    OrderProgress(state = state)
                }
            }

        },
    ){

    }
}