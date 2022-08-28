package com.atitienei_daniel.trasor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.atitienei_daniel.core_navigation.GamesNavigationDestination
import com.atitienei_daniel.feature_games.navigation.gamesGraph
import com.atitienei_daniel.new_game.navigation.newGameGraph
import com.atitienei_daniel.update_game.navigation.updateGameGraph

@Composable
fun TrasorNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = GamesNavigationDestination.route) {
        gamesGraph(
            onNavigate = {
                navController.navigate(it) {
                    launchSingleTop = true
                }
            }
        )
        newGameGraph(
            onBackClick = {
                navController.popBackStack()
            }
        )
        updateGameGraph(
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}