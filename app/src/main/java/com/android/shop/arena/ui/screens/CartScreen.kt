package com.android.shop.arena.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.shop.arena.R
import com.android.shop.arena.ui.components.ProductCard


@Preview
@Composable
fun CartScreen() {
    Column {
        repeat(3){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Box(modifier = Modifier.size(200.dp)){
                    ProductCard(
                        modifier = Modifier.size(120.dp),
                        product = Product(imageResource = R.drawable.gta5_cover, title = "Grand Theft Auto V", description = "description", category = "Action")
                    )
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_minimize_24), contentDescription = "")
                    Text(text = "0")
                    Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "")
                }
            }

        }
    }
}