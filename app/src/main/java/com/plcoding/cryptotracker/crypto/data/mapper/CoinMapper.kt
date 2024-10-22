package com.plcoding.cryptotracker.crypto.data.mapper

import com.plcoding.cryptotracker.crypto.data.dto.CoinDto
import com.plcoding.cryptotracker.crypto.data.dto.CoinHistoryDto
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.domain.CoinHistory
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = this.id,
        name = this.name,
        rank = this.rank,
        marketCapUsd = this.marketCapUsd,
        symbol = this.symbol,
        priceUsd = this.priceUsd,
        changePercent24Hour = this.changePercent24Hr
    )
}

fun CoinHistoryDto.toCoinHistory(): CoinHistory {
    val time = ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.time), ZoneId.of("UTC"))
    return CoinHistory(
        priceUsd = this.priceUsd,
        time = time
    )
}