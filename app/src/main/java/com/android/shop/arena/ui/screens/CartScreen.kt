package com.android.shop.arena.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.shop.arena.R
import com.android.shop.arena.api.removeCartItem
import com.android.shop.arena.api.updateCartItemQuantity
import com.android.shop.arena.data.entity.Game
import com.android.shop.arena.data.pref.DataStoreManager
import com.android.shop.arena.ui.components.Loader
import com.android.shop.arena.ui.theme.CardColor
import com.android.shop.arena.ui.viewmodel.SharedViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.GamesExtraArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalGlideComposeApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen(paddingValues: PaddingValues) {

    val cartViewModel : SharedViewModel = viewModel()
    val games by cartViewModel.games.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()

    val cartItemsDetails = games.sortedBy { it.id }.filter { game ->
        cartItems.sortedBy { it.id }.any { cartItem -> cartItem.id == game.id }
    }
    val totalAmount = cartViewModel.calculateTotalAmount(cartItemsDetails.sortedBy { it.id }, cartItems.sortedBy { it.id })

    val uid = cartViewModel.userId.value


        Scaffold(
            containerColor = Color.White,
            modifier = Modifier
                .padding(paddingValues),

            topBar = {
                     Row {

                     }
            },


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
                        Text(text = "₹ $totalAmount", fontSize = 13.sp, fontWeight = FontWeight.Medium)
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

            if (cartItemsDetails.isEmpty() || uid == "") {
                //Loader(Color.White, Color(0xFF05AF22))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Cart is Empty"
                )
            }
            else{

            LazyColumn(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, it.calculateBottomPadding())
                    .fillMaxSize()
            ) {
                items(cartItems.size) { game ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp, 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(CardColor)
                    ) {
                        GlideImage(
                            modifier = Modifier
                                .size(160.dp)
                                .padding(10.dp),
                            model = cartItemsDetails[game].coverUri,
                            contentDescription = ""
                        )

                        Column(
                            modifier = Modifier
                                .height(160.dp)
                                .padding(10.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = cartItemsDetails[game].name,
                                overflow = TextOverflow.Clip,
                                maxLines = 1,
                                color = Color.Black,
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Text(
                                text = cartItemsDetails[game].category,
                                color = Color(0xFF4848AA),
                                textAlign = TextAlign.Left,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Text(
                                text = "sold by: ${cartItemsDetails[game].soldBy}",
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
                                    modifier = Modifier
                                        .size(17.dp)
                                        .clickable {
                                            CoroutineScope(Dispatchers.IO).launch {
                                                if (cartItems[game].quantity > 1) {
                                                    updateCartItemQuantity(
                                                        cartItems[game].id,
                                                        uid,
                                                        cartItems[game].quantity - 1
                                                    )
                                                } else {
                                                    removeCartItem(cartItemsDetails[game].id, uid)

                                                }
                                                cartViewModel.refreshCartItems()

                                            }

                                        },
                                    painter = painterResource(id = R.drawable.minus),
                                    tint = Color(0xFF66DD7A),
                                    contentDescription = ""
                                )
                                Text(text = cartItems[game].quantity.toString())
                                Icon(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clickable {
                                            CoroutineScope(Dispatchers.IO).launch {
                                                updateCartItemQuantity(
                                                    cartItems[game].id,
                                                    uid,
                                                    cartItems[game].quantity + 1
                                                )
                                                cartViewModel.refreshCartItems()
                                            }

                                        },
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
                                Text(text = cartItemsDetails[game].discountPrice)
                                Text(

                                    color = Color.Gray,
                                    text = cartItemsDetails[game].totalPrice,
                                    textDecoration = TextDecoration.LineThrough
                                )

                            }

                        }
                    }

                }
            }
        }
    }


}













