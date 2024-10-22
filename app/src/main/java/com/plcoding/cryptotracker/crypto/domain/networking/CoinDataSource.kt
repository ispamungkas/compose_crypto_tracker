package com.plcoding.cryptotracker.crypto.domain.networking

import com.plcoding.cryptotracker.crypto.core.domain.utils.NetworkError
import com.plcoding.cryptotracker.crypto.core.domain.utils.Result
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.domain.CoinHistory
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoin(): Result<List<Coin>, NetworkError>
    suspend fun getHistoryCoin(coinId: String, start: ZonedDateTime, end: ZonedDateTime): Result<List<CoinHistory>, NetworkError>
}