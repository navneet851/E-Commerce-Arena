package com.android.shop.arena.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.shop.arena.R
import com.android.shop.arena.data.DataStoreManager
import com.android.shop.arena.ui.components.ProfileMenuItem
import com.android.shop.arena.ui.theme.CardColor
import com.android.shop.arena.ui.theme.InputColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Preview
@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val dataStoreManager = DataStoreManager(context)
    val coroutineScope = rememberCoroutineScope()
    var uid by remember {
        mutableStateOf("user")
    }
//    val uid by dataStoreManager.uidFlow.collectAsState(initial = "user")

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreManager.uidFlow.collect{
                uid = it!!
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(10.dp)
                .clip(shape = RoundedCornerShape(10))
                .background(InputColor)
        ){
            Image(
                modifier = Modifier
                    .size(150.dp)
                    .clip(shape = RoundedCornerShape(50)),
                painter = painterResource(id = R.drawable.arena_logo),
                contentDescription = "profile image")

        }

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp, 0.dp)
                .clip(shape = RoundedCornerShape(5))
                .background(InputColor)
                .padding(5.dp, 10.dp)
        ){
            Text(
                text = uid,
                modifier = Modifier.width(200.dp), // Define a width for the Text composable
                overflow = TextOverflow.Clip, // Clip the overflowed text
                maxLines = Int.MAX_VALUE // Allow the text to use as many lines as needed
            )
            ProfileMenuItem(icon = R.drawable.baseline_list_alt_24, title = "My Details")
            ProfileMenuItem(icon = R.drawable.baseline_wb_shade_24, title = "My Orders")
        }

    }
}