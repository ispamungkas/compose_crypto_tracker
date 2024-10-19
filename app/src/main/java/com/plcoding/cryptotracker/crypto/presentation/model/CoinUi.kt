package com.plcoding.cryptotracker.crypto.presentation.model

import androidx.annotation.DrawableRes
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.util.getDrawableIdForCoin
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id: String,
    val name: String,
    val rank: Int,
    val symbol: String,
    val marketCapUsd: DisplayNumber,
    val priceUsd: DisplayNumber,
    val changePercent24Hour: DisplayNumber,
    @DrawableRes val iconRes: Int
)

data class DisplayNumber(
    val price: Double,
    val formatter: String
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = this.id,
        name = this.name,
        rank = this.rank,
        symbol = this.symbol,
        marketCapUsd = this.marketCapUsd.formatDisplayNumber(),
        priceUsd = this.priceUsd.formatDisplayNumber(),
        changePercent24Hour = this.changePercent24Hour.formatDisplayNumber(),
        iconRes = getDrawableIdForCoin(this.symbol)
    )
}

fun Double.formatDisplayNumber(): DisplayNumber {
    val format = NumberFormat.getInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayNumber(
        price = this,
        formatter = format.format(this)
    )
}
