package com.android.shop.arena.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.shop.arena.R
import com.android.shop.arena.ui.theme.CardColor
import com.android.shop.arena.ui.viewmodel.SharedViewModel

@Preview
@Composable
fun MyOrdersScreen() {

    val myOrdersViewModel : SharedViewModel = viewModel()
    val transactions by myOrdersViewModel.transactions.collectAsState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(700.dp)
                .clip(RoundedCornerShape(40.dp))
                .background(CardColor)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color(0xFFEAFFEE))
                    .fillMaxWidth()
                    .height(230.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.success),
                    modifier = Modifier
                        .padding(5.dp)
                        .size(40.dp),
                    contentDescription = ""
                )
                Text(
                    text = "Amount",
                    fontSize = 12.sp,
                    lineHeight = 1.sp
                )
                Text(
                    text = "₹250",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Column{
                repeat(4){ index ->
                    Text(text = "")
                }
            }





            Column(
                modifier = Modifier
                    .background(Color(0xFFEAFFEE))
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Text(
                    text = "Status: Delivered",
                    fontSize = 18.sp,
                    lineHeight = 1.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Transaction Id: ",
                    color = Color.DarkGray,
                    fontSize = 12.sp,
                    lineHeight = 1.sp
                )
                Text(
                    text = "2r34r3r34343r43 ",
                    fontSize = 13.sp,
                    lineHeight = 1.sp
                )

                Text(
                    text = "Received at 8.55 Pm, 30 jun 2024 ",
                    fontSize = 13.sp
                )

            }

        }
    }
}