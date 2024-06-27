package com.android.shop.arena.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.shop.arena.data.entity.Game
import com.android.shop.arena.ui.theme.CardColor
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductCard(modifier: Modifier = Modifier, game : Game, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
           horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(CardColor)
                .padding(5.dp)
                .clickable {
                    onClick()
                }
        ) {
            GlideImage(
                model = game.coverUri,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth()
            )

            Text(
                text = game.category,
                color = Color(0xFF4848AA),
                textAlign = TextAlign.Left,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 5.dp))


            Text(
                text = game.name,
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp, 5.dp, 10.dp)
            )

        }
    }
}
