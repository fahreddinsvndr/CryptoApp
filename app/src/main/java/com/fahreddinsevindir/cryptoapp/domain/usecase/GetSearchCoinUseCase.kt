package com.fahreddinsevindir.cryptoapp.domain.usecase

import com.fahreddinsevindir.cryptoapp.common.Resource
import com.fahreddinsevindir.cryptoapp.data.model.search_coins.CryptoResponse
import com.fahreddinsevindir.cryptoapp.data.remote.CoinService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSearchCoinUseCase @Inject constructor(
    private val coinService: CoinService
) {
    operator fun invoke(limit: Int, start: Int): Flow<Resource<CryptoResponse>> = flow {

        try {
            emit(Resource.Loading())
            emit(Resource.Success(coinService.getSearchCoins(limit, start)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }
}