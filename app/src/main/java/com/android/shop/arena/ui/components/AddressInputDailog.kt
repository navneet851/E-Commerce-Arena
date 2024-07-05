package com.android.shop.arena.ui.components

import android.provider.CalendarContract
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.android.shop.arena.data.entity.Address
import com.android.shop.arena.ui.theme.InputColor

@Composable
fun AddressInputDialog(showDialog: MutableState<Boolean>, onSave: (Address) -> Unit) {
    if (showDialog.value) {
        var name by remember { mutableStateOf("") }
        var mobile by remember { mutableStateOf("") }
        var flat by remember { mutableStateOf("") }
        var city by remember { mutableStateOf("") }
        var state by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }
        var pinCode by remember { mutableStateOf("") }

        AlertDialog(
            containerColor = Color.White,
            textContentColor = Color.Black,
            titleContentColor = Color.Black,
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Add Address") },
            text = {
                Column {

                    val colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color(0xFF00C41E),
                        focusedIndicatorColor = Color(0xFF00C41E),
                        focusedLeadingIconColor = Color.Black,
                        unfocusedLeadingIconColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        cursorColor = Color.Black,
                    )


                    TextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, colors = colors)
                    TextField(value = mobile, onValueChange = { mobile = it }, label = { Text("Mobile Number") }, colors = colors)
                    TextField(value = flat, onValueChange = { flat = it }, label = { Text("Flat, House No., Building") }, colors = colors)
                    TextField(value = city, onValueChange = { city = it }, label = { Text("City, Town, Village") }, colors = colors)
                    TextField(value = state, onValueChange = { state = it }, label = { Text("State") }, colors = colors)
                    TextField(value = country, onValueChange = { country = it }, label = { Text("Country") }, colors = colors)
                    TextField(value = pinCode, onValueChange = { pinCode = it }, label = { Text("Pin Code") }, colors = colors)
                }
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEAFFEE),
                        contentColor = Color.Black
                    ),
                    onClick = {
                        val address = Address(name, mobile, flat, city, state, country, pinCode)
                        onSave(address)
                        showDialog.value = false
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEAFFEE),
                        contentColor = Color.Black
                    ),
                    onClick = { showDialog.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}