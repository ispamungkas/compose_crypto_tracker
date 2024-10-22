package com.plcoding.cryptotracker.crypto.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponseCoinHistoryDto(
    val data: List<CoinHistoryDto>
)
