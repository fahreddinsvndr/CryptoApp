package com.fahreddinsevindir.cryptoapp.navigation

sealed class Screen(val route: String) {

    object SplashScreen : Screen("splash_screen")
    object SearchCoinScreen : Screen("search_coin_screen")

    object CoinDetailScreen : Screen("coin_detail_screen")


}