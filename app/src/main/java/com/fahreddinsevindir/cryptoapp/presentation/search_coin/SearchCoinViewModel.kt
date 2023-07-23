package com.fahreddinsevindir.cryptoapp.presentation.search_coin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahreddinsevindir.cryptoapp.common.Resource
import com.fahreddinsevindir.cryptoapp.data.model.search_coins.CryptoModel
import com.fahreddinsevindir.cryptoapp.domain.usecase.GetSearchCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchCoinViewModel @Inject constructor(
    private val searchCoinUseCase: GetSearchCoinUseCase
) : ViewModel() {


    private val _state = mutableStateOf(SearchCoinState())
    val state: State<SearchCoinState> = _state

    private var _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery
    private var allCoin = listOf<CryptoModel>()

    init {
        getSearchCoins(1000,1)
    }



    private fun getSearchCoins(limit: Int, start: Int) {
        searchCoinUseCase(limit, start).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = SearchCoinState(cryptoModel = it.data)
                        allCoin = it.data as List<CryptoModel>
                    }
                }
                is Resource.Loading -> {
                    _state.value =  SearchCoinState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = SearchCoinState(error = "Bir sorun oldu !")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchCoin(query: String) {
        _searchQuery.value = query
        _state.value = SearchCoinState(isLoading = true)

        val foundCoin =
            allCoin.filter {
                it.symbol!!.contains(query, true) or
                        it.name!!.contains(query, true)
            }

        if (foundCoin.isEmpty()) {
            _state.value =
                SearchCoinState(error = "Not Found ?")
            return
        }

        _state.value = SearchCoinState(cryptoModel = foundCoin)

    }

    fun clearSearchQuery() {
        _state.value = SearchCoinState(cryptoModel = allCoin)
        _searchQuery.value = ""
        return
    }



}