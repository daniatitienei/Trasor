package com.atitienei_daniel.update_game.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.atitienei_daniel.core_navigation.TrasorNavigationDestination
import com.atitienei_daniel.update_game.UpdateGameRoute

object UpdateGameNavigationDestination : TrasorNavigationDestination {
    override val route: String = "update_game_route/gameId?={gameId}"
}

fun NavGraphBuilder.updateGameGraph(
    onBackClick: () -> Unit
) =
    composable(UpdateGameNavigationDestination.route) {
        UpdateGameRoute(
            onBackClick = onBackClick
        )
    }