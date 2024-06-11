package com.android.shop.arena.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.android.shop.arena.R
import com.android.shop.arena.auth.checkPhoneNumberInDatabase
import com.android.shop.arena.auth.checkUserCredentialsInDatabase
import com.android.shop.arena.auth.onLoginClicked
import com.android.shop.arena.auth.storedVerificationId
import com.android.shop.arena.auth.verifyPhoneNumberWithCode
import com.android.shop.arena.ui.components.InputField
import com.android.shop.arena.ui.components.Loader
import com.android.shop.arena.ui.components.PasswordInputField
import com.android.shop.arena.ui.theme.InputColor


@Composable
fun LoginScreen(navController: NavHostController) {

    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var otpRequestProgressed by remember { mutableStateOf(true) }
    var otpReceived by remember { mutableStateOf(false) }
    var loginWithPass by remember { mutableStateOf(true) }
    val context = LocalContext.current
    var notExist by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = phoneNumber) {
        if (phoneNumber.length == 10){
            checkPhoneNumberInDatabase(phoneNumber){
                if (!it){
                    notExist = true
                }
            }
        }
        else{
            notExist = false
        }
    }

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

    Text(
        text = "skip",
        fontSize = 13.sp,
        color = Color.White,
        textAlign = TextAlign.Right,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .padding(16.dp, 10.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("home")
            }
        ,
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Login",

            fontSize = 26.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(10.dp),
        )
        Spacer(modifier = Modifier.height(40.dp))
        Column {
            InputField(inputType = "Phone Number", leadingIcon = painterResource(id = R.drawable.baseline_phone_24), phoneNumber, onTextChange = { phoneNumber = it })

            if(notExist){
                Text(
                    text = "No Account Found",
                    fontSize = 12.sp,
                    color = Color.Red,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(16.dp, 0.dp)
                    ,
                )
            }
        }
        if (loginWithPass){
            PasswordInputField(inputType = "Password", leadingIcon = painterResource(id = R.drawable.baseline_lock_24), password = password, onPasswordChange = { password = it }, confirmPassword = password, onConfirmPasswordChange = { })
        }
        else{
            if (otpReceived){
                InputField(inputType = "Otp", leadingIcon = painterResource(id = R.drawable.baseline_numbers_24), otp, onTextChange = { otp = it })
            }
            else{
                Button(
                    modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = InputColor,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        if (notExist || phoneNumber.isBlank() || phoneNumber.length < 10 || phoneNumber.length > 10){
                            Toast.makeText(context, "Enter Valid Number", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            onLoginClicked(context = context, phoneNumber = phoneNumber){
                                otpRequestProgressed = it
                                otpReceived = it
                            }
                            otpRequestProgressed = false
                        }
                    }

                ) {
                    Text(text = "Send Otp")
                }
            }
        }


        if(loginWithPass){
            Text(
                text = "Login With otp",
                fontSize = 15.sp,
                color = Color(0xFF107c0f),
                modifier = Modifier
                    .padding(20.dp)
                    .clickable {
                               loginWithPass = false
                    }
                ,
            )
        }

        else{
            Text(
                text = "Login With Password",
                fontSize = 15.sp,
                color = Color(0xFF107c0f),
                modifier = Modifier
                    .padding(20.dp)
                    .clickable {
                               loginWithPass = true
                    }
                ,
            )
        }


        if(otpReceived || loginWithPass){
            Button(
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = InputColor,
                    contentColor = Color.Black
                ),
                onClick = {

                    if (notExist){
                        Toast.makeText(context, "Register First", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        if(loginWithPass){
                            if (password.isBlank() && phoneNumber.isBlank()){
                                Toast.makeText(context, "Enter Valid Details!", Toast.LENGTH_SHORT).show()

                            }
                            else{
                                checkUserCredentialsInDatabase(phoneNumber = phoneNumber, password = password){
                                    if(it){
                                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                        navController.navigate("home")
                                    }
                                    else{
                                        Toast.makeText(context, "Wrong Password", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }

                        }
                        else{
                            if (otp.isBlank() || otp.length < 6 || otp.length > 6){
                                Toast.makeText(context, "Enter Six Digit Number", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                verifyPhoneNumberWithCode(context, storedVerificationId, otp){
                                    if (it == "failed"){
                                        Toast.makeText(context, "Wrong Otp", Toast.LENGTH_SHORT).show()
                                        otpRequestProgressed = true
                                    }
                                    else{
                                        navController.navigate("home")
                                        otpRequestProgressed = true



                                    }
                                }
                                otpRequestProgressed = false
                            }

                        }

                    }

                }

            ) {
                Text(text = "Login")
            }
        }



        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Register",
            fontSize = 14.sp,
            color = Color.White,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(16.dp, 10.dp)
                .clickable {
                    navController.navigate("register")
                }
            ,
        )



    }

    if (!otpRequestProgressed) {
        Loader()
    }

}
