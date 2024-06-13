package com.android.shop.arena

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.android.shop.arena.data.pref.DataStoreManager
import com.android.shop.arena.ui.theme.ArenaTheme

class MainActivity : ComponentActivity() {

    lateinit var dataStoreManager: DataStoreManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStoreManager = DataStoreManager(this)
        enableEdgeToEdge()
        setContent {
            ArenaTheme {
                App()
            }
        }
    }
}

