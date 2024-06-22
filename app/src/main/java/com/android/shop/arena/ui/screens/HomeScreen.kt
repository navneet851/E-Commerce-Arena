package com.android.shop.arena.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.android.shop.arena.R
import com.android.shop.arena.ui.components.ProductCard


@Composable
fun HomeScreen(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(8.dp, 0.dp)
            ,
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

}




data class Product(
    val imageResource: Int,
    val title: String,
    val description: String,
    val category: String
)