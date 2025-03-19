package com.plcoding.cryptotracker.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinResponseDto (
    val data : List<CoinDto>
)