package com.fahreddinsevindir.cryptoapp.data.remote

import com.fahreddinsevindir.cryptoapp.data.model.search_coins.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinService {

    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getSearchCoins(
        @Query("limit") limit: Int,
        @Query("start") start: Int
    ): CryptoResponse


}