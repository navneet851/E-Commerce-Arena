package com.android.shop.arena.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.android.shop.arena.data.entity.Address

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
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Add Address") },
            text = {
                Column {


                    TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                    TextField(value = mobile, onValueChange = { mobile = it }, label = { Text("Mobile") })
                    TextField(value = flat, onValueChange = { flat = it }, label = { Text("Flat") })
                    TextField(value = city, onValueChange = { city = it }, label = { Text("City") })
                    TextField(value = state, onValueChange = { state = it }, label = { Text("State") })
                    TextField(value = country, onValueChange = { country = it }, label = { Text("Country") })
                    TextField(value = pinCode, onValueChange = { pinCode = it }, label = { Text("Pin Code") })
                }
            },
            confirmButton = {
                Button(
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
                Button(onClick = { showDialog.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}