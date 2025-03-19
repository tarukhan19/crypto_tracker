package com.plcoding.cryptotracker.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.plcoding.cryptotracker.presentation.models.CoinUi

@Immutable
data class CoinListState(
    val isLoading : Boolean = false,
    val coins : List<CoinUi> = emptyList(),
    val selectedCoinUi : CoinUi? = null
)