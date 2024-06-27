package com.android.shop.arena.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.android.shop.arena.R
import com.android.shop.arena.ui.components.Loader
import com.android.shop.arena.ui.components.ProductCard
import com.android.shop.arena.ui.navigation.Product
import com.android.shop.arena.ui.viewmodel.SharedViewModel


@Composable
fun HomeScreen(paddingValues: PaddingValues, navController: NavController) {

    val homeViewModel : SharedViewModel = viewModel()
    val games by homeViewModel.games.collectAsState()

    Log.d("Games", games.toString())

    Box(
        modifier = Modifier
            .padding(paddingValues)
    ) {

        if(games.isEmpty()){
            Loader()
        }
        else{
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(8.dp, 0.dp)
                ,
                columns = GridCells.Fixed(2)
            ) {
                items(games.size) {
                    ProductCard(
                        modifier = Modifier.size(160.dp),
                        game = games[it]
                    ){
                        navController.navigate(Product(games[it].id))
                    }
                }

            }
        }

    }

}




