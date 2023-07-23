package com.fahreddinsevindir.cryptoapp.presentation.search_coin

import com.fahreddinsevindir.cryptoapp.data.model.search_coins.CryptoModel

data class SearchCoinState(
    val isLoading: Boolean = false,
    var cryptoModel: List<CryptoModel?>? = null,
    val error: String = ""
)