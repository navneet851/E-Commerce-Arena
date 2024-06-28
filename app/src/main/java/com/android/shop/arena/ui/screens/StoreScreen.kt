package com.android.shop.arena.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.shop.arena.ui.components.ProductPagerCard
import com.android.shop.arena.ui.navigation.Product
import com.android.shop.arena.ui.viewmodel.SharedViewModel


@Composable
fun StoreScreen(paddingValues: PaddingValues, navController: NavHostController) {

    val storeViewModel : SharedViewModel = viewModel()
    val games by storeViewModel.games.collectAsState()


    LazyColumn(
        modifier = Modifier.padding(paddingValues),
    ) {
        items(games.size){
            ProductPagerCard(
                game = games.reversed()[it]
            ){
                navController.navigate(Product(games.reversed()[it].id))
            }
        }
    }

}


