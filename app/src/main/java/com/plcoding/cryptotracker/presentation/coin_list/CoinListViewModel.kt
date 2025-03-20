package com.plcoding.cryptotracker.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptotracker.core.domain.util.onError
import com.plcoding.cryptotracker.core.domain.util.onSuccess
import com.plcoding.cryptotracker.domain.CoinDataSource
import com.plcoding.cryptotracker.presentation.models.CoinUi
import com.plcoding.cryptotracker.presentation.models.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    private val _event = Channel<CoinListEvent>()
    val events = _event.receiveAsFlow()

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )


    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.onCoinClick -> {
             selectCoin(action.coinUi)
            }
        }
    }

    private fun selectCoin(coinUi: CoinUi) {
        _state.update { it.copy(selectedCoinUi = coinUi) }
        viewModelScope.launch {
            coinDataSource.getCoinHistory(
                coinId = coinUi.id,
                start = ZonedDateTime.now().minusDays(5),
                end = ZonedDateTime.now(),
            )
                .onSuccess { history ->
                    println(history)
                }
                .onError { error ->
                    _event.send(CoinListEvent.Error(error))
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
                _event.send(CoinListEvent.Error(error))
            }
        }
    }
}