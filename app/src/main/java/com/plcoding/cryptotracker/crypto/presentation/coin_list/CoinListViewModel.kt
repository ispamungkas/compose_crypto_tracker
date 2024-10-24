package com.plcoding.cryptotracker.crypto.presentation.coin_list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptotracker.crypto.core.domain.utils.onError
import com.plcoding.cryptotracker.crypto.core.domain.utils.onSuccess
import com.plcoding.cryptotracker.crypto.core.presentation.utils.errorToDisplayString
import com.plcoding.cryptotracker.crypto.domain.networking.CoinDataSource
import com.plcoding.cryptotracker.crypto.presentation.model.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    private val _state = MutableStateFlow<CoinListState>(CoinListState())
    val state = _state
        .onStart { getCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    private val _event = Channel<CoinListEvent>()
    val event = _event.receiveAsFlow()

    fun onItemClick(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnClickAction -> {
                val coin = action.coin
                _state.update {
                    it.copy(
                        selectedCoin = coin
                    )
                }
            }
        }
    }

    fun getListCoinHistory(context: Context, coinId: String) {
        val startDate = ZonedDateTime.now().minusDays(5)
        val endDate = ZonedDateTime.now()
        viewModelScope.launch {
            coinDataSource.getHistoryCoin(
                coinId = coinId,
                start = startDate,
                end = endDate
            ).onSuccess { result ->
                println(result)
            }.onError { errorResult ->
                println(errorResult.errorToDisplayString(context))
            }
        }
    }

    private fun getCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            coinDataSource.getCoin()
                .onSuccess { res ->
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            coin = res.map { it.toCoinUi() }
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                    _event.send(CoinListEvent.Error(e = error))
                }
        }
    }

}