package com.plcoding.cryptotracker.crypto.data.mapper

import com.plcoding.cryptotracker.crypto.data.dto.CoinDto
import com.plcoding.cryptotracker.crypto.domain.Coin

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