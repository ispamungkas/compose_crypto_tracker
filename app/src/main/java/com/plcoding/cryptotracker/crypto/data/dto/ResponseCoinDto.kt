package com.plcoding.cryptotracker.crypto.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponseCoinDto(
    val data: List<CoinDto>
)
