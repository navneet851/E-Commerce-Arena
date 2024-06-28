package com.android.shop.arena.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.shop.arena.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.shop.arena.ui.components.Loader
import com.android.shop.arena.ui.viewmodel.SharedViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import com.bumptech.glide.integration.compose.placeholder


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun ProductScreen(id: Int, navController: NavHostController) {

    val productViewModel : SharedViewModel = viewModel()
    val games by productViewModel.games.collectAsState()
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
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            navController.navigateUp()
                        },
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = "back"
                    )
                }
            )
        }
    ) {

        if (games.isEmpty()) {
            Loader()
        }
        else{
            val game by remember {
                mutableStateOf(games[id])
            }
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color.Black)
                    .verticalScroll(rememberScrollState())
            ) {
                GlideImage(
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .size(250.dp),
                    model = game.coverUri,
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
                        text = game.name,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = game.category,
                        color = Color(0xFF5F72EC),
                        textAlign = TextAlign.Left,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Sold By: ${game.soldBy}",
                        color = Color.Gray,
                        textAlign = TextAlign.Left,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Row(
                        modifier = Modifier.padding(0.dp, 10.dp)
                    ) {
                        Text(text = "MRP  ", letterSpacing = 0.sp, fontSize = 18.sp, color = Color.Gray)
                        Text(text = "₹${game.totalPrice}", textDecoration = TextDecoration.LineThrough, letterSpacing = 0.sp, fontSize = 18.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
                        Text(text = "  ₹${game.discountPrice}", letterSpacing = 0.sp, fontSize = 18.sp, color = Color.LightGray, fontWeight = FontWeight.Medium)
                    }


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
                            .height(250.dp)
                            .background(Color.Black)
                            .verticalScroll(rememberScrollState())
                    ) {

                        Text(
                            text = game.description,
                            color = Color.Gray,
                            fontSize = 13.sp,
                            letterSpacing = 0.sp,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

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

                    val pagerState = rememberPagerState {
                        game.screenshots.size
                    }
                    HorizontalPager(
                        modifier = Modifier

                            .clip(RoundedCornerShape(0.dp)),
                        state = pagerState) {page ->
                        GlideImage(
                            model = game.screenshots[page],
                            loading = placeholder(R.drawable.landscape_placeholder),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
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

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "System Requirements",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(0.dp, 10.dp)
                            .fillMaxWidth()
                    )

                    Column(
                        modifier = Modifier.padding(10.dp)
                    ){
                        Row {
                            Text(text = "OS: ", letterSpacing = 0.sp, fontSize = 12.sp, color = Color.Gray)
                            Text(text = game.support[0], letterSpacing = 0.sp, fontSize = 12.sp, color = Color.LightGray)
                        }
                        Row {
                            Text(text = "Processor: ", fontSize = 12.sp, color = Color.Gray)
                            Text(text = game.support[1], letterSpacing = 0.sp, fontSize = 12.sp, color = Color.LightGray)
                        }
                        Row {
                            Text(text = "Memory: ", fontSize = 12.sp, color = Color.Gray)
                            Text(text = game.support[2], letterSpacing = 0.sp, fontSize = 12.sp, color = Color.LightGray)
                        }
                        Row {
                            Text(text = "Graphics: ", fontSize = 12.sp, color = Color.Gray)
                            Text(text = game.support[3], letterSpacing = 0.sp, fontSize = 12.sp, color = Color.LightGray)
                        }
                        Row {
                            Text(text = "Storage: ", fontSize = 12.sp, color = Color.Gray)
                            Text(text = game.support[4], letterSpacing = 0.sp, fontSize = 12.sp, color = Color.LightGray)
                        }
                    }

                }
            }

        }
    }

}