package com.android.shop.arena.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.sp
import com.android.shop.arena.R
import com.android.shop.arena.ui.theme.CardColor


@Composable
fun ProfileMenuItem(icon: Int, title: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(CardColor)
            .padding(5.dp)
            .clickable {
                onClick()
            }
    ) {
        Icon(
            modifier = Modifier.size(25.dp),
            tint = Color.Black,
            painter = painterResource(id = icon),
            contentDescription = title
        )

        Text(
            color = Color.Black,
            text = title,
            fontSize = 15.sp,
            modifier = Modifier.width(260.dp)
        )

        Icon(
            modifier = Modifier.size(22.dp),
            tint = Color.Black,
            painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
            contentDescription = "open"
        )
    }
}