package com.android.shop.arena.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.android.shop.arena.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputField(
    inputType : String,
    leadingIcon : Painter,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit
) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 10.dp)
            .clip(RoundedCornerShape(10.dp))
        ,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
        ),
        value = password,
        onValueChange = onPasswordChange,
        label = {
            Text(text = inputType)
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            Icon(painter = leadingIcon, contentDescription = "Enter $inputType")
        },
        isError = password != confirmPassword && confirmPassword.isNotBlank(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = if (passwordVisible) {
                            painterResource(id = R.drawable.baseline_visibility_24)
                    }
                    else{
                        painterResource(id = R.drawable.baseline_visibility_off_24)
                        }, contentDescription = "password"
                )
            }
        }
    )
    if (password != confirmPassword && confirmPassword.isNotBlank()) {
        Text(text = "Passwords do not match", color = Color.Red)
    }
}