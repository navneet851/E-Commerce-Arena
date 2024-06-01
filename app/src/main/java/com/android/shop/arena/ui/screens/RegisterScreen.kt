package com.android.shop.arena.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.shop.arena.R
import com.android.shop.arena.ui.components.InputField
import com.android.shop.arena.ui.components.PasswordInputField

@Preview(showBackground = true)
@Composable
private fun RegisterScreen() {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
       // verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Create An Account")
        InputField(inputType = "Name", leadingIcon = painterResource(id = R.drawable.baseline_person_24))
        InputField(inputType = "Email", leadingIcon = painterResource(id = R.drawable.baseline_email_24))
        PasswordInputField(inputType = "Password", leadingIcon = painterResource(id = R.drawable.baseline_lock_24), password = password, onPasswordChange = { password = it }, confirmPassword = confirmPassword, onConfirmPasswordChange = { confirmPassword = it })
        PasswordInputField(inputType = "Confirm Password", leadingIcon = painterResource(id = R.drawable.baseline_lock_24), password = confirmPassword, onPasswordChange = { confirmPassword = it }, confirmPassword = password, onConfirmPasswordChange = { password = it })
    }
}

