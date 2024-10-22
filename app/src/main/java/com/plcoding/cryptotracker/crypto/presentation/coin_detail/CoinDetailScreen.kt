package com.plcoding.cryptotracker.crypto.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.crypto.core.presentation.utils.getDrawableIdForCoin
import com.plcoding.cryptotracker.crypto.presentation.coin_detail.component.InfoCard
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListState
import com.plcoding.cryptotracker.crypto.presentation.coin_list.component.coin
import com.plcoding.cryptotracker.crypto.presentation.model.DisplayNumber
import com.plcoding.cryptotracker.crypto.presentation.model.formatDisplayNumber
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import com.plcoding.cryptotracker.ui.theme.greenBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    state: CoinListState,
    modifier: Modifier = Modifier
) {
    val coin = state.selectedCoin
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
        }
    } else if (coin != null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = getDrawableIdForCoin(coin.symbol)),
                contentDescription = coin.name,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = coin.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = contentColor
            )
            Text(
                text = coin.symbol,
                fontWeight = FontWeight.Thin,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = contentColor
            )
            FlowRow(
                horizontalArrangement = Arrangement.Center
            ) {
                InfoCard(
                    title = "Market Cap",
                    formatterText = coin.marketCapUsd.formatter,
                    icon = ImageVector.vectorResource(
                        id = R.drawable.stock
                    )
                )
                InfoCard(
                    title = "Price",
                    formatterText = "$ ${coin.priceUsd.formatter}",
                    icon = ImageVector.vectorResource(
                        id = R.drawable.dollar
                    )
                )
                val absoluteChangeFormatter = (coin.priceUsd.price * (coin.changePercent24Hour.price / 100)).formatDisplayNumber()
                val isPositive = coin.changePercent24Hour.price > 0.0
                val contentColor = if (isPositive) {
                    if (isSystemInDarkTheme()) Color.Green else greenBackground
                } else {
                    MaterialTheme.colorScheme.error
                }
                InfoCard(
                    title = "Current Update",
                    formatterText = absoluteChangeFormatter.formatter,
                    icon = if (isPositive) {
                        ImageVector.vectorResource(id = R.drawable.trending)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.trending_down).apply {
                            tintColor.red
                        }
                    },
                    contentColor = contentColor
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinDetailScreenPreview() {
    CryptoTrackerTheme {
        CoinDetailScreen(
            state = CoinListState(
                selectedCoin = coin.copy(
                    changePercent24Hour = DisplayNumber(price = -1.5, formatter = "-1.5")
                )
            ),
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.background
            )
        )
    }
}