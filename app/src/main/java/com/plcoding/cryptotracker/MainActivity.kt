package com.plcoding.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.cryptotracker.crypto.core.presentation.utils.errorToDisplayString
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import com.plcoding.cryptotracker.crypto.presentation.coin_list.coinListErrorListener
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val coinViewModel = koinViewModel<CoinListViewModel>()
                    val state by coinViewModel.state.collectAsStateWithLifecycle()

                    val context = LocalContext.current
                    coinListErrorListener (event = coinViewModel.event) { event ->
                        when(event){
                            is CoinListEvent.Error -> {
                                Toast.makeText(
                                    context,
                                    event.e.errorToDisplayString(context),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    CoinListScreen(state = state, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
