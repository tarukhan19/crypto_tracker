package com.demo.cryptotracker.crypto.presentation.coin_list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.demo.cryptotracker.core.presentation.util.ObserveAsEvents
import com.demo.cryptotracker.core.presentation.util.toStrings
import com.demo.cryptotracker.crypto.domain.Coin
import com.demo.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.demo.cryptotracker.crypto.presentation.models.toCoinUi
import com.demo.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoinListScreen(
    state: CoinListState,
    coinListViewModel: CoinListViewModel = koinViewModel(),
    onAction: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    ObserveAsEvents(coinListViewModel.events) { event ->
        when (event) {
            is CoinListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.networkError.toStrings(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = { onAction(CoinListAction.onCoinClick(coinUi)) },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }

}


@PreviewLightDark
@Composable
private fun CoinListItemPreview() {
    CryptoTrackerTheme {
        CoinListItem(
            coinUi = previewCoin,
            onClick = { },
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}

internal val previewCoin = Coin(
    id = "bitcoin",
    rank = 1,
    name = "Bitcoin",
    symbol = "BTC",
    marketCapUsd = 1241273958896.75,
    priceUsd = 62828.15,
    changePercent24Hr = 0.1
).toCoinUi()