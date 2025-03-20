package com.plcoding.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.cryptotracker.presentation.coin_detail.CoinDetailScreen
import com.plcoding.cryptotracker.presentation.coin_list.CoinListScreen
import com.plcoding.cryptotracker.presentation.coin_list.CoinListViewModel
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
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