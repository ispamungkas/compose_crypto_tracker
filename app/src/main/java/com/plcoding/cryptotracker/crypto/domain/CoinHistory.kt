package com.plcoding.cryptotracker.crypto.domain

import java.time.ZonedDateTime

data class CoinHistory(
    val priceUsd: Double,
    val time: ZonedDateTime
)
