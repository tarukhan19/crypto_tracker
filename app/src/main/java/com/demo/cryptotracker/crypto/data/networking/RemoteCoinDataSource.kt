package com.demo.cryptotracker.crypto.data.networking

import com.demo.cryptotracker.core.domain.util.NetworkError
import com.demo.cryptotracker.core.domain.util.Result
import com.demo.cryptotracker.core.domain.util.map
import com.demo.cryptotracker.crypto.data.mappers.toCoin
import com.demo.cryptotracker.crypto.data.mappers.toCoinPrice
import com.demo.cryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.demo.cryptotracker.crypto.data.networking.dto.CoinResponseDto
import com.demo.cryptotracker.crypto.domain.Coin
import com.demo.cryptotracker.crypto.domain.CoinDataSource
import com.demo.cryptotracker.crypto.domain.CoinPrice
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(private val api: CoinApiService ) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            api.getCoins("300c840e7cb566eb4a5b0d4c6e91be75fb64fcefb1b241fa234ec78dfd21e5b0")
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
        val endMillis = end.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()

        return safeCall<CoinHistoryDto> {
            api.getCoinHistory(coinId, startMillis = startMillis, endMillis = endMillis)
        }. map { response ->
            response.data.map { it.toCoinPrice() }

        }
    }
}