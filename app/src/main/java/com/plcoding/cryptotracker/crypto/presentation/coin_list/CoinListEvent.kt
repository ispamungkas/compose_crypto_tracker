package com.plcoding.cryptotracker.crypto.presentation.coin_list

import com.plcoding.cryptotracker.crypto.core.domain.utils.NetworkError

sealed interface CoinListEvent{
    data class Error(val e: NetworkError): CoinListEvent
}