package com.plcoding.cryptotracker.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.plcoding.cryptotracker.crypto.presentation.coin_list.component.CoinListItem
import com.plcoding.cryptotracker.crypto.presentation.coin_list.component.coin
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListScreen(
    state: CoinListState,
    modifier: Modifier = Modifier,
    onItemClick: (CoinListAction) -> Unit
) {
    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coin) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = {
                        onItemClick(CoinListAction.OnClickAction(coinUi))
                    },
                )
                HorizontalDivider()
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListScreenPreview() {
    CryptoTrackerTheme {
        CoinListScreen(
            state = CoinListState(
                coin = (1..100).map {
                    coin.copy(id = it.toString())
                },
                isLoading = false
            ),
            modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
            onItemClick = {}
        )
    }
}