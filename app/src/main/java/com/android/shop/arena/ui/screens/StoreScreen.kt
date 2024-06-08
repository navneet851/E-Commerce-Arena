package com.android.shop.arena.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.android.shop.arena.R
import com.android.shop.arena.ui.components.ProductCard
import com.android.shop.arena.ui.components.ProductPagerCard

@Preview
@Composable
fun StoreScreen() {
    val productPagerImage = listOf(
        R.drawable.gta5_slide,
        R.drawable.gta5_slide1,
        R.drawable.gta5_slide2,
        R.drawable.gta5_slide3,
        )


    LazyColumn {
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