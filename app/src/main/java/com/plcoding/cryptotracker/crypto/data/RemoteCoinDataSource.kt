package com.plcoding.cryptotracker.crypto.data

import com.plcoding.cryptotracker.crypto.core.domain.utils.NetworkError
import com.plcoding.cryptotracker.crypto.core.domain.utils.Result
import com.plcoding.cryptotracker.crypto.core.domain.utils.map
import com.plcoding.cryptotracker.crypto.core.networking.constructUrl
import com.plcoding.cryptotracker.crypto.core.networking.safeCall
import com.plcoding.cryptotracker.crypto.data.dto.ResponseCoinDto
import com.plcoding.cryptotracker.crypto.data.mapper.toCoin
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.domain.networking.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

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

}