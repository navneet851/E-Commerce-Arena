package com.android.shop.arena.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.shop.arena.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.android.shop.arena.ui.components.ProductPagerCard
import com.android.shop.arena.ui.theme.CardColor


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ProductScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    navigationIconContentColor = Color.White,
                ),
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {

                        },
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = "back"
                    )
                }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .size(250.dp),
                painter = painterResource(id = R.drawable.gta5_cover),
                contentDescription = ""
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(40.dp, 40.dp, 0.dp, 0.dp))
                    .background(Color(0x2C4D4B4B))
                    .padding(20.dp)

            ){
                Text(
                    text = "Grand Theft Auto V",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "Action",
                    color = Color(0xFF5F72EC),
                    textAlign = TextAlign.Left,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "sold by: Rockstar Games",
                    color = Color.Gray,
                    textAlign = TextAlign.Left,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                    ,
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xCE05AF22),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(text = "ADD TO CART")
                }

                Text(
                    text = "Description",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(Color.Black)
                        .verticalScroll(rememberScrollState())
                ) {

                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        color = Color.Gray,
                        fontSize = 13.sp,
                        letterSpacing = 0.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                Text(
                    text = "Screenshots",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .fillMaxWidth()
                )

                val pagerImg = listOf(
                    R.drawable.gta5_slide,
                    R.drawable.gta5_slide1,
                    R.drawable.gta5_slide2,
                    R.drawable.gta5_slide3,
                )
                val pagerState = rememberPagerState {
                    pagerImg.size
                }
                HorizontalPager(
                    modifier = Modifier

                        .clip(RoundedCornerShape(0.dp)),
                    state = pagerState) {page ->
                    Image(
                        painter = painterResource(id = pagerImg[page]),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
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
                        val color = if (pagerState.currentPage == iteration) Color.LightGray else Color.DarkGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(5.dp)
                        )
                    }
                }
            }
        }
    }

}