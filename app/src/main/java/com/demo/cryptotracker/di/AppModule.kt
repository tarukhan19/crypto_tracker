package com.demo.cryptotracker.di

import com.demo.cryptotracker.crypto.data.networking.CoinApiService
import com.demo.cryptotracker.crypto.data.networking.RemoteCoinDataSource
import com.demo.cryptotracker.crypto.data.networking.RetrofitFactory
import com.demo.cryptotracker.crypto.domain.CoinDataSource
import com.demo.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { RetrofitFactory.create() } // returns Retrofit
    single { get<Retrofit>().create(CoinApiService::class.java) }

    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()
    viewModelOf(::CoinListViewModel)
    }
