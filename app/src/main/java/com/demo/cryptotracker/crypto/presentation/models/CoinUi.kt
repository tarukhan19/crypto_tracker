package com.demo.cryptotracker.crypto.presentation.models

import androidx.annotation.DrawableRes
import com.demo.cryptotracker.core.presentation.util.getDrawableIdForCoin
import com.demo.cryptotracker.crypto.domain.Coin
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id : String,
    val rank : Int,
    val name : String,
    val symbol : String,
    val marketCapUsd : DisplayableNumber,
    val priceUsd : DisplayableNumber,
    val changePercent24Hr : DisplayableNumber,
    @DrawableRes val iconRes : Int
)

data class DisplayableNumber(
    val value : Double,
    val formatted : String
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        priceUsd = priceUsd.toDisplayableNumber()?: DisplayableNumber(0.0, "0.00"),
        marketCapUsd = marketCapUsd.toDisplayableNumber()?: DisplayableNumber(0.0, "0.00"),
        changePercent24Hr = changePercent24Hr?.toDisplayableNumber() ?: DisplayableNumber(0.0, "0.00"),
        iconRes = getDrawableIdForCoin(symbol),
    )
}


fun Double.toDisplayableNumber(): DisplayableNumber? {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}