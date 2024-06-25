package com.android.shop.arena.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.shop.arena.R
import com.android.shop.arena.ui.components.ProductCard
import com.android.shop.arena.ui.theme.CardColor


@Preview
@Composable
fun CartScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        repeat(3){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(CardColor)
            ){
                Image(
                    modifier = Modifier
                        .size(160.dp)
                        .padding(10.dp),
                    painter = painterResource(id = R.drawable.gta5_cover),
                    contentDescription = "")

                Column(
                    modifier = Modifier
                        .height(160.dp)
                        .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Gta 5",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Action",
                        color = Color(0xFF4848AA),
                        textAlign = TextAlign.Left,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "sold by: jfnf",
                        color = Color.Gray,
                        textAlign = TextAlign.Left,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .width(100.dp)
                            .height(28.dp)
                            .clip(RoundedCornerShape(7.dp))
                            .background(Color(0xFFEAFFEE))
                            .padding(8.dp, 0.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(17.dp),
                            painter = painterResource(id = R.drawable.minus),
                            tint = Color(0xFF66DD7A),
                            contentDescription = "")
                        Text(text = "1")
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.plus),
                            tint = Color(0xFF66DD7A),
                            contentDescription = "")
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(5.dp)
                            .width(130.dp)
                            .height(28.dp)
                    ) {
                        Text(text = "5,999")
                        Text(

                            color = Color.Gray,
                            text = "7,599",
                            textDecoration = TextDecoration.LineThrough
                        )

                    }

                }
            }

        }
    }
}












