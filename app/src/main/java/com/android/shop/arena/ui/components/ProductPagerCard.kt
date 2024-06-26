package com.android.shop.arena.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.shop.arena.ui.screens.Product
import com.android.shop.arena.ui.screens.ProductPager
import com.android.shop.arena.ui.theme.CardColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductPagerCard(modifier: Modifier = Modifier, product: ProductPager) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
           horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(CardColor)
                .padding(8.dp)
        ) {

            val pagerState = rememberPagerState {
                product.pagerImg.size
            }
            val coroutineScope = rememberCoroutineScope()

            LaunchedEffect(key1 = pagerState) {
                while (true){
                    delay(2000)
                    val nextPage = (pagerState.currentPage + 1) % product.pagerImg.size
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(nextPage)
                    }
                }
            }
            HorizontalPager(
                modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                state = pagerState) {page ->
                Image(
                    painter = painterResource(id = product.pagerImg[page]),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = modifier
                        .clip(RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                )
            }
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(5.dp)
                    )
                }
            }


            Text(
                text = "Action",
                color = Color(0xFF4848AA),
                textAlign = TextAlign.Left,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp))


            Text(
                text = product.title,
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp, 5.dp, 10.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth().padding(5.dp, 0.dp)
            ) {
                Text(
                    text = "Rs:${product.price.toString()}",
                    color = Color.Gray,
                    textDecoration = TextDecoration.LineThrough
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = "Rs:${product.discountPrice.toString()}",
                    color = Color.Black,
                )

            }

        }
    }
}
