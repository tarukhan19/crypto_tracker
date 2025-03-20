package com.demo.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demo.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.demo.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.demo.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import com.demo.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val vm = koinViewModel<CoinListViewModel>()
                    val state = vm.state.collectAsStateWithLifecycle()
                    when {
                        state.value.selectedCoinUi != null ->
                            CoinDetailScreen(
                                state = state.value,
                                modifier = Modifier.padding(innerPadding)
                            )
                        else -> CoinListScreen(
                            state = state.value,
                            modifier = Modifier.padding(innerPadding),
                            onAction = vm::onAction
                        )
                    }
                }
            }
        }
    }
}