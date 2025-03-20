package com.demo.cryptotracker.crypto.presentation.coin_list

import com.demo.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error (val networkError : NetworkError) : CoinListEvent
}