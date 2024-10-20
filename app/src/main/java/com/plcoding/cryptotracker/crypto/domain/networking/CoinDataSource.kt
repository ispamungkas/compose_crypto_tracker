package com.plcoding.cryptotracker.crypto.domain.networking

import com.plcoding.cryptotracker.crypto.core.domain.utils.NetworkError
import com.plcoding.cryptotracker.crypto.core.domain.utils.Result
import com.plcoding.cryptotracker.crypto.domain.Coin

interface CoinDataSource {
    suspend fun getCoin(): Result<List<Coin>, NetworkError>
}