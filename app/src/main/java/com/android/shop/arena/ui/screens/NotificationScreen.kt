package com.android.shop.arena.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.shop.arena.R
import com.android.shop.arena.ui.components.NotificationCard
import com.android.shop.arena.ui.components.OrderCard
import com.android.shop.arena.ui.navigation.Order
import com.android.shop.arena.ui.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavHostController) {

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(10.dp, 0.dp),
                title = {
                    Text(
                        modifier = Modifier.padding(10.dp, 0.dp),
                        text = "Notifications",
                        fontSize = 16.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
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
        }
    ) { it ->

        val notificationViewModel : SharedViewModel = viewModel()
        val transactions by notificationViewModel.transactions.collectAsState()

        if (transactions.isEmpty()){
            //Loader()
            Text(
                modifier = Modifier.padding(it).fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                text = "No Notifications"
            )
        }
        else{
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState())
            ) {
                repeat(transactions.size){ order ->
                    NotificationCard(transactions[order]){
                        navController.navigate(Order(transactions[order].dateAndTime))
                    }
                }
            }
        }



    }
}