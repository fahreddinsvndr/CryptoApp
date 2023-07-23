package com.fahreddinsevindir.cryptoapp.presentation.search_coin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahreddinsevindir.cryptoapp.common.componet.SearchBar
import com.fahreddinsevindir.cryptoapp.ui.theme.Orj

@Composable
fun SearchCoinScreen(
    viewModel: SearchCoinViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
) {

    val state = viewModel.state.value
    val searchQuery = viewModel.searchQuery.value

    Box {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(
                searchText = searchQuery,
                placeholderText = "Search Coins...",
                onSearchTextChanged = {
                    viewModel.searchCoin(it)
                },
                onClearClick = {
                    viewModel.clearSearchQuery()
                })


            LazyColumn {
                state.cryptoModel.let {listCryptoModel->
                    items(listCryptoModel ?: emptyList()) { coinName ->
                        SearchCoinItem(
                            coin = coinName,
                            onItemClick = {cryptoModel->
                                navigateToDetail.invoke(cryptoModel)
                            }
                        )

                    }
                }

            }
        }
    }

    if (state.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(state.error, Modifier.align(Alignment.Center))
        }

    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Orj
            )
        }

    }
}

