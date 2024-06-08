package com.android.shop.arena.ui.screens

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.shop.arena.R
import com.android.shop.arena.ui.components.ProductCard
import com.android.shop.arena.ui.theme.CardColor

@PreviewScreenSizes
@Composable
fun HomeScreen() {
    LazyVerticalGrid(
        modifier = Modifier.padding(8.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(10) {
            ProductCard(
                modifier = Modifier.size(160.dp),
                product = Product(imageResource = R.drawable.gta5_cover, title = "Grand Theft Auto V", description = "description", category = "Action")
            )
        }

    }
}




data class Product(
    val imageResource: Int,
    val title: String,
    val description: String,
    val category: String
)