package com.demo.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.demo.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.demo.cryptotracker.crypto.presentation.coin_list.CoinListAction
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
                CryptoTrackerApp()
            }
        }
    }

    @Composable
    fun CryptoTrackerApp() {
        val navController = rememberNavController()
        val vm: CoinListViewModel = koinViewModel()
        val state = vm.state.collectAsStateWithLifecycle()

        CryptoTrackerTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "coin_list",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("coin_list") {
                        CoinListScreen(
                            state = state.value,
                            onAction = { action ->
                                vm.onAction(action)
                                if (action is CoinListAction.onCoinClick) {
                                    navController.navigate("coin_detail/${action.coinUi.id}")
                                }
                            }
                        )
                    }
                    composable(
                        route = "coin_detail/{coinId}",
                        arguments = listOf(navArgument("coinId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        CoinDetailScreen(
                            state = state.value,
                        )
                    }
                }
            }
        }
    }

}