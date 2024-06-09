package com.android.shop.arena.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.shop.arena.R
import com.android.shop.arena.auth.onLoginClicked
import com.android.shop.arena.auth.storedVerificationId
import com.android.shop.arena.auth.verifyPhoneNumberWithCode
import com.android.shop.arena.ui.components.InputField
import com.android.shop.arena.ui.components.Loader
import com.android.shop.arena.ui.components.PasswordInputField
import com.android.shop.arena.ui.theme.CardColor


@Composable
fun RegisterScreen(navController: NavHostController) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isDialogVisible by remember { mutableStateOf(false) }
    var name by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var otpRequestProgressed by remember { mutableStateOf(true) }

    var otp by remember { mutableStateOf("") }




    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(5.dp),
            painter = painterResource(id = R.drawable.sign_in_up),
            contentScale = ContentScale.Crop,
            contentDescription = "sign_in_up_background")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Create An Account",

            fontSize = 26.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(10.dp),
        )
        Spacer(modifier = Modifier.height(40.dp))
        InputField(inputType = "Name", leadingIcon = painterResource(id = R.drawable.baseline_person_24), name, onTextChange = { name = it })
        InputField(inputType = "Phone Number", leadingIcon = painterResource(id = R.drawable.baseline_phone_24), phoneNumber, onTextChange = { phoneNumber = it })
        PasswordInputField(inputType = "Password", leadingIcon = painterResource(id = R.drawable.baseline_lock_24), password = password, onPasswordChange = { password = it }, confirmPassword = confirmPassword, onConfirmPasswordChange = { confirmPassword = it })
        PasswordInputField(inputType = "Confirm Password", leadingIcon = painterResource(id = R.drawable.baseline_lock_24), password = confirmPassword, onPasswordChange = { confirmPassword = it }, confirmPassword = password, onConfirmPasswordChange = { password = it })
        Button(
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            onClick = {
                onLoginClicked(context = context, phoneNumber = phoneNumber){
                    otpRequestProgressed = it
                    isDialogVisible = it
                }
                otpRequestProgressed = false


        }
        ) {
            Text(text = "Send Otp")
        }


        if (!otpRequestProgressed) {
            Loader() // Replace this with your actual loader composable
        }
    }







    //dialog box

    if (isDialogVisible) {
        Log.d("phoneBook", "code senttopt-----$storedVerificationId")
        AlertDialog(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            onDismissRequest = { isDialogVisible = false },
            title = { Text(text = "Enter OTP") },
            text = {
                TextField(
                    value = otp,
                    onValueChange = { otp = it },
                    label = { Text("OTP") },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CardColor,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        verifyPhoneNumberWithCode(context, storedVerificationId, otp)
                        Log.d("phoneBook", "$context $storedVerificationId $otp")
                        navController.navigate("home")
                    }
                ) {
                    Text("Verify")
                }
            }
        )
    }
}






data class User(
    val name: String,
    val phone: String,
    val password: String
)
