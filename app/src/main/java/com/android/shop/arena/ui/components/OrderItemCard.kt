package com.android.shop.arena.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.shop.arena.data.entity.OrderedItem
import com.android.shop.arena.ui.theme.OrderPalette

@Composable
fun OrderItemCard(orderItems: OrderedItem) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp, 5.dp)
            .fillMaxWidth()
            .heightIn(min = 50.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(OrderPalette)
            .padding(10.dp,8.dp)
    ) {
        Text(
            text = orderItems.name,
            color = Color.Black,
            fontSize = 16.sp
        )
        Text(
            text = "x${orderItems.quantity}",
            color = Color.Black
        )
    }
}