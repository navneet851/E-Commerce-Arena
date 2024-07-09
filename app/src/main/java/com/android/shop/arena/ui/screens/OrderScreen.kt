package com.android.shop.arena.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.shop.arena.R
import com.android.shop.arena.api.deleteAddressByFlat
import com.android.shop.arena.ui.components.Loader
import com.android.shop.arena.ui.components.OrderItemCard
import com.android.shop.arena.ui.theme.CardColor
import com.android.shop.arena.ui.theme.OrderPalette
import com.android.shop.arena.ui.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(dateAndTime: String, navController: NavHostController) {

    val orderViewModel : SharedViewModel = viewModel()
    val transactions by orderViewModel.transactions.collectAsState()
    val order = transactions.filter {
        it.dateAndTime == dateAndTime
    }

    Scaffold(
        containerColor = OrderPalette,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(OrderPalette)
                    .padding(10.dp, 0.dp),
                title = {
                    Text(
                        modifier = Modifier.padding(10.dp, 0.dp),
                        text = "",
                        fontSize = 16.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = OrderPalette,
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
        }
    ) {
        if (order.isEmpty()){
            Loader()
        }
        else{

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    //.background(Color.White)
                    .padding(it)
            ){

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(30.dp, 0.dp)
                        .fillMaxWidth()
                        .heightIn(min = 600.dp)
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
                            contentDescription = "success order"
                        )
                        Text(
                            text = "Amount",
                            fontSize = 12.sp,
                            lineHeight = 1.sp
                        )
                        Text(
                            text = "₹ ${order[0].amount}",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Column(
                        modifier = Modifier.heightIn(min = 200.dp)
                    ){
                        repeat(order[0].orderedItem.size){ index ->
                            OrderItemCard(order[0].orderedItem[index])
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            //.padding(10.dp, 5.dp)
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Color(0xFFEAFFEE)
                            )
                            .padding(10.dp, 5.dp)
                    ) {

                        Column {
                            Text(text = "On Address:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            Text(text = order[0].address.name, fontSize = 13.sp, lineHeight = 1.sp)
                            Text(text = order[0].address.mobile, fontSize = 13.sp, lineHeight = 1.sp)
                            Text(text = order[0].address.flat, fontSize = 13.sp, lineHeight = 1.sp)
                            Text(text = order[0].address.city, fontSize = 13.sp, lineHeight = 1.sp)
                            Text(text = order[0].address.state, fontSize = 13.sp, lineHeight = 1.sp)
                            Text(text = order[0].address.country, fontSize = 13.sp, lineHeight = 1.sp)
                            Text(text = order[0].address.pinCode, fontSize = 13.sp, lineHeight = 1.sp)
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Checkbox(
                                checked = true,
                                onCheckedChange = {

                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFF00C41E),
                                    checkmarkColor = Color.White
                                )
                            )
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
                            color = Color(0xFF00C41E),
                            text = "Status: Delivered",
                            fontSize = 18.sp,
                            lineHeight = 1.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Transaction Id: ",
                            color = Color.Gray,
                            fontSize = 12.sp,
                            lineHeight = 1.sp
                        )
                        Text(
                            text = order[0].transactionId,
                            fontSize = 12.sp,
                            lineHeight = 1.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Received at ${order[0].dateAndTime}",
                            fontSize = 13.sp
                        )

                    }

                }
            }
        }

    }



}