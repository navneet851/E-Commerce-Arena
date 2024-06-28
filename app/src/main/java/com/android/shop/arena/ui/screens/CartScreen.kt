package com.android.shop.arena.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.shop.arena.R
import com.android.shop.arena.data.pref.DataStoreManager
import com.android.shop.arena.ui.theme.CardColor
import com.android.shop.arena.ui.viewmodel.SharedViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen(paddingValues: PaddingValues, dataStore: DataStoreManager) {

    val uid by dataStore.uidFlow.collectAsState(initial = "")

    val cartViewModel : SharedViewModel = viewModel()
    val cartItems by cartViewModel.cartItems.collectAsState()

    Scaffold(
        containerColor = Color.White,
        modifier = Modifier
            .padding(paddingValues),
        bottomBar = {
            Column(
                modifier = Modifier
                    .background(Color(0xFFEAFFEE))
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 10.dp, 10.dp, 0.dp)
                ){
                    Text(text = "Total MRP", fontSize = 12.sp)
                    Text(text = "₹ 10,000", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp)
                ){
                    Text(text = "Shipping Fee", fontSize = 12.sp)
                    Text(text = "FREE", fontSize = 12.sp, color = Color(0xFF05AF22))
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                    ,
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF05AF22),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(text = "PLACE ORDER")
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, it.calculateBottomPadding())
                .fillMaxSize()
        ) {
            items(cartItems.size) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(CardColor)
                ) {
                    Image(
                        modifier = Modifier
                            .size(160.dp)
                            .padding(10.dp),
                        painter = painterResource(id = R.drawable.gta5_cover),
                        contentDescription = ""
                    )

                    Column(
                        modifier = Modifier
                            .height(160.dp)
                            .padding(10.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Gta 5",
                            color = Color.Black,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Text(
                            text = "Action",
                            color = Color(0xFF4848AA),
                            textAlign = TextAlign.Left,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Text(
                            text = "sold by: jfnf",
                            color = Color.Gray,
                            textAlign = TextAlign.Left,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .width(100.dp)
                                .height(28.dp)
                                .clip(RoundedCornerShape(7.dp))
                                .background(Color(0xFFEAFFEE))
                                .padding(8.dp, 0.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(17.dp),
                                painter = painterResource(id = R.drawable.minus),
                                tint = Color(0xFF66DD7A),
                                contentDescription = ""
                            )
                            Text(text = "1")
                            Icon(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.plus),
                                tint = Color(0xFF66DD7A),
                                contentDescription = ""
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(5.dp)
                                .width(130.dp)
                                .height(28.dp)
                        ) {
                            Text(text = "5,999")
                            Text(

                                color = Color.Gray,
                                text = "7,599",
                                textDecoration = TextDecoration.LineThrough
                            )

                        }

                    }
                }

            }
        }
    }
}













