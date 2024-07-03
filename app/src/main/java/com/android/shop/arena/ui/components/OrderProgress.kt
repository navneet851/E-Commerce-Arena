package com.android.shop.arena.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.shop.arena.data.entity.OrderState


@Composable
fun OrderProgress(state : List<OrderState>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(state.size){
            LinearProgress(state[it].color, state[it].text)
        }
    }
}

@Composable
fun LinearProgress(color: Color, text: String = "") {
    LinearProgressIndicator(
        modifier = Modifier
            .padding(5.dp, 0.dp)
            .width(70.dp)
            .height(1.dp),
        trackColor = color
        )
    Text(
        text = text,
        color = Color.Gray,
        fontSize = 9.sp,
        fontWeight = FontWeight.Medium
    )
}