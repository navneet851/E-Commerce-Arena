package com.android.shop.arena.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.shop.arena.R
import com.android.shop.arena.ui.components.ProductPagerCard


@Composable
fun StoreScreen(paddingValues: PaddingValues) {
    val productPagerImage = listOf(
        R.drawable.gta5_slide,
        R.drawable.gta5_slide1,
        R.drawable.gta5_slide2,
        R.drawable.gta5_slide3,
        )


    LazyColumn(
        modifier = Modifier.padding(paddingValues),
    ) {
        items(5){
            ProductPagerCard(
                product = ProductPager(
                    coverImg = R.drawable.gta5_cover,
                    pagerImg = productPagerImage,
                    title = "Grand Theft Auto V", price = 43433,
                    discountPrice = 23434, category = "Action"
                )
            )
        }
    }

}


data class ProductPager(
    val coverImg: Int,
    val pagerImg : List<Int>,
    val title: String,
    val price : Int,
    val discountPrice : Int,
    val category: String
)