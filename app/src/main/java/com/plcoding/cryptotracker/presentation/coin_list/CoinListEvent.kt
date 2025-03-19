package com.plcoding.cryptotracker.presentation.coin_list

import com.plcoding.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error (val networkError : NetworkError) : CoinListEvent
}