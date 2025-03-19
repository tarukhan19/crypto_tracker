package com.plcoding.cryptotracker.presentation.coin_list

import com.plcoding.cryptotracker.presentation.models.CoinUi

sealed interface CoinListAction {
    data class onCoinClick (val coinUi: CoinUi) : CoinListAction
}