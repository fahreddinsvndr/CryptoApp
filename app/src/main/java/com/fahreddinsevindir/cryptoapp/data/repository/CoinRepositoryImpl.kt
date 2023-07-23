package com.fahreddinsevindir.cryptoapp.data.repository

import com.fahreddinsevindir.cryptoapp.data.model.search_coins.CryptoResponse
import com.fahreddinsevindir.cryptoapp.data.remote.CoinService
import com.fahreddinsevindir.cryptoapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val coinService: CoinService) :
    CoinRepository {
    override suspend fun getSearchCoin(limit: Int, start: Int): CryptoResponse =
        coinService.getSearchCoins(limit, start)


}