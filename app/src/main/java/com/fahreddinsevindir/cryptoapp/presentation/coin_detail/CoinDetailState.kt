package com.fahreddinsevindir.cryptoapp.presentation.coin_detail

import com.fahreddinsevindir.cryptoapp.data.model.search_coins.CryptoModel

data class CoinDetailState(
    val isLoading: Boolean = false,
    var cryptoDetail: CryptoModel? = null,
    val error: String = ""
)