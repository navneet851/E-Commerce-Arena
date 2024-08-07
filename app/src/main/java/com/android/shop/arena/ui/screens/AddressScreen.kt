package com.android.shop.arena.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.android.shop.arena.R
import com.android.shop.arena.api.addTransactionToFirestore
import com.android.shop.arena.api.deleteAddressByFlat
import com.android.shop.arena.api.saveAddressToFirestore
import com.android.shop.arena.data.entity.OrderState
import com.android.shop.arena.data.entity.OrderedItem
import com.android.shop.arena.data.entity.Transaction
import com.android.shop.arena.ui.components.AddressInputDialog
import com.android.shop.arena.ui.components.Loader
import com.android.shop.arena.ui.components.OrderProgress
import com.android.shop.arena.ui.navigation.Order
import com.android.shop.arena.ui.theme.CardColor
import com.android.shop.arena.ui.theme.InputColor
import com.android.shop.arena.ui.viewmodel.SharedViewModel
import com.google.firebase.messaging.FirebaseMessaging

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(navController: NavController, appPadding: PaddingValues) {

    val orderViewModel : SharedViewModel = viewModel()
    val games by orderViewModel.games.collectAsState()
    val cartItems by orderViewModel.cartItems.collectAsState()
    val addresses by orderViewModel.address.collectAsState()

    val uid = orderViewModel.userId.value

    val cartItemsDetails = games.sortedBy { it.id }.filter { game ->
        cartItems.sortedBy { it.id }.any { cartItem -> cartItem.id == game.id }
    }
    val orderedItem = mutableListOf<OrderedItem>()
    cartItemsDetails.forEach {
        orderedItem.add(OrderedItem(it.name, cartItems[cartItemsDetails.indexOf(it)].quantity))
    }

    val totalAmount = orderViewModel.calculateTotalAmount(cartItemsDetails.sortedBy { it.id }, cartItems.sortedBy { it.id })

    var checkBoxIndex by remember {
        mutableStateOf(0)
    }

    var showLoader by remember {
        mutableStateOf(false)
    }

    if (showLoader){
        Loader()
    }
    else{
        Scaffold(
            modifier = Modifier.padding( 0.dp, 0.dp, 0.dp, appPadding.calculateBottomPadding()),
            containerColor = Color.White,

            topBar = {
                Column {
                    TopAppBar(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(10.dp, 0.dp),
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

            bottomBar = {

                var token by remember{
                    mutableStateOf<String?>(null)
                }

                FirebaseMessaging.getInstance().token.addOnSuccessListener {
                    token = it
                }
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
                        val quantity = cartItems.sumOf {
                            it.quantity
                        }
                        Text(text = "Item", fontSize = 12.sp)
                        Text(text = "x${quantity}", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }
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
                            .padding(10.dp, 10.dp, 10.dp, 30.dp)
                        ,
                        onClick = {
                            if (addresses.isEmpty()){
                                Toast.makeText(navController.context, "Choose Address", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                val time = orderViewModel.getCurrentDateTimeFormatted()
                                showLoader = true
                                val transaction = Transaction(
                                    totalAmount.toString(),
                                    time,
                                    uid,
                                    addresses[checkBoxIndex],
                                    true,
                                    orderedItem
                                )
                                addTransactionToFirestore(transaction){
                                    showLoader = false
                                    navController.navigate(Order(time = time))
                                    if (token != null) {
                                        Log.d("Token", "Token: $token")
                                        orderViewModel.sendMessage(token, "Order", "Order Received")
                                    }

                                }

                            }

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF05AF22),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(text = "PROCEED TO PAY")
                    }
                }
            }




        ){ it ->
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState())
            ) {


                val showDialog = remember { mutableStateOf(false) }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                    ,
                    onClick = {
                        showDialog.value = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = InputColor,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(text = "Add Address", color = Color.Black, fontSize = 13.sp)

                }

                if (addresses.isEmpty()){
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        text = "No Address Found"
                    )
                }
                else{
                    repeat(addresses.size){ index ->

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(10.dp, 5.dp)
                                .fillMaxWidth()
                                .height(130.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    if (checkBoxIndex == index) Color(0xFFEAFFEE) else CardColor
                                )
                                .padding(10.dp, 5.dp)
                        ) {
                            Column {

                                Text(text = addresses[index].name, fontSize = 13.sp, lineHeight = 1.sp)
                                Text(text = addresses[index].mobile, fontSize = 13.sp, lineHeight = 1.sp)
                                Text(text = addresses[index].flat, fontSize = 13.sp, lineHeight = 1.sp)
                                Text(text = addresses[index].city, fontSize = 13.sp, lineHeight = 1.sp)
                                Text(text = addresses[index].state, fontSize = 13.sp, lineHeight = 1.sp)
                                Text(text = addresses[index].country, fontSize = 13.sp, lineHeight = 1.sp)
                                Text(text = addresses[index].pinCode, fontSize = 13.sp, lineHeight = 1.sp)
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxHeight()
                            ) {
                                Checkbox(
                                    checked = checkBoxIndex == index,
                                    onCheckedChange = {
                                        checkBoxIndex = if (it) index else 0
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Color(0xFF00C41E),
                                        checkmarkColor = Color.White
                                    )
                                )
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "delete",
                                    Modifier
                                        .padding(0.dp, 10.dp)
                                        .clickable {
                                            deleteAddressByFlat(addresses[index].flat, uid) {
                                                orderViewModel.refreshAddresses()
                                                checkBoxIndex = 0
                                            }
                                        },
                                    tint = Color.Black
                                )
                            }


                        }
                    }
                }



                AddressInputDialog(showDialog = showDialog, onSave = { address ->
                    if (uid != ""){
                        val addresss = address.copy(uid = uid)
                        saveAddressToFirestore(addresss){
                            orderViewModel.refreshAddresses()
                        }
                    }
                })

            }

        }
    }


}

