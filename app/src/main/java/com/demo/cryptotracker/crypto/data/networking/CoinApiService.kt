package com.demo.cryptotracker.crypto.data.networking

import com.demo.cryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.demo.cryptotracker.crypto.data.networking.dto.CoinResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinApiService {
    @GET("assets")
    suspend fun getCoins(): Response<CoinResponseDto>

    @GET("assets/{id}/history")
    suspend fun getCoinHistory(
        @Path("id") coinId: String,
        @Query("interval") interval: String = "h6",
        @Query("start") startMillis: Long,
        @Query("end") endMillis: Long
    ): Response<CoinHistoryDto>
}
