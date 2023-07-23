package com.fahreddinsevindir.cryptoapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fahreddinsevindir.cryptoapp.common.Constants
import com.fahreddinsevindir.cryptoapp.data.model.search_coins.CryptoModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state


    init {
        savedStateHandle.get<String>(Constants.CRYPTO_MODEL)?.let { cryptoModel ->
          getCoinDetails(cryptoModel = cryptoModel)
        }

    }


   private fun getCoinDetails(cryptoModel: String){
        val apiData = Gson().fromJson(cryptoModel, CryptoModel::class.java)
        _state.value = CoinDetailState(cryptoDetail = apiData)
    }




}