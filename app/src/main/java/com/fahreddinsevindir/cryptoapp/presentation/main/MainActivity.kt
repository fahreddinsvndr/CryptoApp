package com.fahreddinsevindir.cryptoapp.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.fahreddinsevindir.cryptoapp.navigation.NavGraph
import com.fahreddinsevindir.cryptoapp.ui.theme.CryptoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                NavGraph(navController = navController)
            }
        }
    }
}
