package com.fahreddinsevindir.cryptoapp.domain.repository

import com.fahreddinsevindir.cryptoapp.data.model.search_coins.CryptoResponse

interface CoinRepository {

    suspend fun getSearchCoin(limit:Int,start: Int): CryptoResponse



}