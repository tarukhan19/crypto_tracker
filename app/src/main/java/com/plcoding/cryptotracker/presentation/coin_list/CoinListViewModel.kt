package com.plcoding.cryptotracker.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptotracker.core.domain.util.onError
import com.plcoding.cryptotracker.core.domain.util.onSuccess
import com.plcoding.cryptotracker.domain.CoinDataSource
import com.plcoding.cryptotracker.presentation.models.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(viewModelScope ,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState())


    private fun onAction(action: CoinListAction) {
        when(action) {
            is CoinListAction.onCoinClick -> {

            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            coinDataSource.getCoins().onSuccess { coins ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        coins = coins.map { it.toCoinUi() }
                    )
                }

            }.onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}