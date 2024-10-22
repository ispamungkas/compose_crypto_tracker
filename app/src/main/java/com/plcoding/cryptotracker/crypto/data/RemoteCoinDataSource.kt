package com.plcoding.cryptotracker.crypto.data

import com.plcoding.cryptotracker.crypto.core.domain.utils.NetworkError
import com.plcoding.cryptotracker.crypto.core.domain.utils.Result
import com.plcoding.cryptotracker.crypto.core.domain.utils.map
import com.plcoding.cryptotracker.crypto.core.networking.constructUrl
import com.plcoding.cryptotracker.crypto.core.networking.safeCall
import com.plcoding.cryptotracker.crypto.data.dto.ResponseCoinDto
import com.plcoding.cryptotracker.crypto.data.dto.ResponseCoinHistoryDto
import com.plcoding.cryptotracker.crypto.data.mapper.toCoin
import com.plcoding.cryptotracker.crypto.data.mapper.toCoinHistory
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.domain.CoinHistory
import com.plcoding.cryptotracker.crypto.domain.networking.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {

    override suspend fun getCoin(): Result<List<Coin>, NetworkError> {
        return safeCall<ResponseCoinDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getHistoryCoin(coinId: String, start: ZonedDateTime, end: ZonedDateTime): Result<List<CoinHistory>, NetworkError> {
        val startMillis = start.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
        val endMillis = end.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
        return safeCall<ResponseCoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/${coinId}/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinHistory() }
        }
    }

}