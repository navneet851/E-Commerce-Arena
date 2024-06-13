package com.android.shop.arena.ui.screens

import android.util.Log
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.shop.arena.R
import com.android.shop.arena.auth.addUser
import com.android.shop.arena.auth.checkPhoneNumberInDatabase
import com.android.shop.arena.auth.onLoginClicked
import com.android.shop.arena.auth.storedVerificationId
import com.android.shop.arena.auth.verifyPhoneNumberWithCode
import com.android.shop.arena.data.pref.DataStoreManager
import com.android.shop.arena.ui.components.InputField
import com.android.shop.arena.ui.components.Loader
import com.android.shop.arena.ui.components.PasswordInputField
import com.android.shop.arena.ui.theme.CardColor
import com.android.shop.arena.ui.theme.InputColor
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavHostController, dataStore: DataStoreManager) {

    val coroutineScope = rememberCoroutineScope()

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
    var alreadyExist by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = phoneNumber) {
        if (phoneNumber.length == 10){
            checkPhoneNumberInDatabase(phoneNumber){
                alreadyExist = it
            }
        }
        else{
            alreadyExist = false
        }
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



    Text(
        text = "Skip",
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
            text = "Create An Account",

            fontSize = 26.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(10.dp),
        )
        Spacer(modifier = Modifier.height(40.dp))
        InputField(inputType = "Name", leadingIcon = painterResource(id = R.drawable.baseline_person_24), name, onTextChange = { name = it })

        Column {
            InputField(inputType = "Phone Number", leadingIcon = painterResource(id = R.drawable.baseline_phone_24), phoneNumber, onTextChange = { phoneNumber = it })

            if(alreadyExist){
                Text(
                    text = "Number already exist",
                    fontSize = 12.sp,
                    color = Color.Red,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(16.dp, 0.dp)
                    ,
                )
            }
        }

        PasswordInputField(inputType = "Password", leadingIcon = painterResource(id = R.drawable.baseline_lock_24), password = password, onPasswordChange = { password = it }, confirmPassword = confirmPassword, onConfirmPasswordChange = { confirmPassword = it })
        PasswordInputField(inputType = "Confirm Password", leadingIcon = painterResource(id = R.drawable.baseline_lock_24), password = confirmPassword, onPasswordChange = { confirmPassword = it }, confirmPassword = password, onConfirmPasswordChange = { password = it })
        Button(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = InputColor,
                contentColor = Color.Black
            ),
            onClick = {
                if(alreadyExist){
                    Toast.makeText(context, "Number already exist", Toast.LENGTH_SHORT).show()
                }
                else{
                    if (phoneNumber.isNotBlank() && (phoneNumber.length == 10) && name.isNotBlank() && (password.isNotBlank() == confirmPassword.isNotBlank())) {
                        onLoginClicked(context = context, phoneNumber = phoneNumber){
                            isDialogVisible = it
                            otpRequestProgressed = it
                        }
                        otpRequestProgressed = false
                    } else {
                        Toast.makeText(context, "Enter Valid Details!", Toast.LENGTH_SHORT).show()
                    }

                }

        }
        ) {
            Text(text = "Send Otp")
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Login",
            fontSize = 14.sp,
            color = Color.White,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(16.dp, 10.dp)
                .clickable {
                    navController.navigate("login")
                }
            ,
        )


    }

    if (!otpRequestProgressed) {
        Loader()
    }





    //dialog box

    if (isDialogVisible) {
        Log.d("Firebase", "code in dialog-----$storedVerificationId")
        AlertDialog(
            containerColor = InputColor,
            titleContentColor = Color.Black,
            onDismissRequest = { isDialogVisible = false },
            title = { Text(text = "Enter OTP") },
            text = {
                TextField(
                    value = otp,
                    onValueChange = { otp = it },
                    label = { Text("OTP") },
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        focusedLeadingIconColor = Color.Black,
                        unfocusedLeadingIconColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black,
                        unfocusedContainerColor = InputColor,
                        focusedContainerColor = InputColor,
                        cursorColor = Color.Black,
                    ),
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
                        if (otp.isBlank() || otp.length < 6 || otp.length > 6){
                            Toast.makeText(context, "Enter Six Digit Number", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            verifyPhoneNumberWithCode(context, storedVerificationId, otp){
                                if (it == "failed"){
                                    Toast.makeText(context, "Wrong Otp", Toast.LENGTH_SHORT).show()
                                }
                                else{
                                    val user = User(it, name, phoneNumber, password)
                                    addUser(user){

                                        val token = it

                                        coroutineScope.launch {
                                            dataStore.saveUID(token)
                                        }
                                    }
                                    navController.navigate("home")
                                    isDialogVisible = false
                                }

                            }

                        }

                    }
                ) {
                    Text("Verify")
                }
            }
        )
    }
}








data class User(
    val uid: String,
    val name: String,
    val phone: String,
    val password: String
)
