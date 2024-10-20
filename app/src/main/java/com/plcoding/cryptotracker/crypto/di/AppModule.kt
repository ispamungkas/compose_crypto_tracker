package com.plcoding.cryptotracker.crypto.di

import com.plcoding.cryptotracker.crypto.core.networking.HttpClientFactory
import com.plcoding.cryptotracker.crypto.data.RemoteCoinDataSource
import com.plcoding.cryptotracker.crypto.domain.networking.CoinDataSource
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}