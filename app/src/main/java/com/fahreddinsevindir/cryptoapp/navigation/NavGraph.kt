package com.fahreddinsevindir.cryptoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fahreddinsevindir.cryptoapp.presentation.coin_detail.CoinDetailScreen
import com.fahreddinsevindir.cryptoapp.presentation.search_coin.SearchCoinScreen
import com.fahreddinsevindir.cryptoapp.presentation.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
    ) {

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                navigateToSearchCoinScreen = {
                    navController.navigate(Screen.SearchCoinScreen.route){
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                }

            )
        }


        composable(route = Screen.SearchCoinScreen.route){
            SearchCoinScreen(
                navigateToDetail = {
                    navController.navigate("${Screen.CoinDetailScreen.route}/$it")
                }
            )
        }

        composable(route = "${Screen.CoinDetailScreen.route}/{coinDetail}"){
            CoinDetailScreen(

            )
        }
    }
}