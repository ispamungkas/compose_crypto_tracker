package com.plcoding.cryptotracker.crypto.presentation.coin_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.presentation.model.CoinUi
import com.plcoding.cryptotracker.crypto.presentation.model.toCoinUi
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListItem(
    coinUi: CoinUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = coinUi.iconRes),
            contentDescription = coinUi.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(85.dp),
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = coinUi.symbol, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = contentColor)
            Text(text = coinUi.name, fontSize = 14.sp, fontWeight = FontWeight.Thin, color = contentColor)
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(text = "$ ${coin.priceUsd.formatter}", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = contentColor)
            PriceChangeCoin(displayNumber = coinUi.changePercent24Hour)
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewCoinListItem() {
    CryptoTrackerTheme {
        CoinListItem(coinUi = coin, onClick = { /*TODO*/ }, modifier = Modifier.background(color = MaterialTheme.colorScheme.background))
    }
}

internal val coin = Coin(
    id = "1",
    symbol = "BTC",
    name = "Bitcoin",
    rank = 1,
    changePercent24Hour = 0.1,
    priceUsd = 1221.12,
    marketCapUsd = 124125121.12
).toCoinUi()