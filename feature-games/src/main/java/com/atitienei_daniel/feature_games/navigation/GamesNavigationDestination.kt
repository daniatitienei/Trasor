package com.atitienei_daniel.feature_games.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.atitienei_daniel.core_navigation.TrasorNavigationDestination
import com.atitienei_daniel.feature_games.GamesRoute

object GamesNavigationDestination : TrasorNavigationDestination {
    override val route: String = "games_route"
}

fun NavGraphBuilder.gamesGraph(
    navigateToNewGame: () -> Unit,
    navigateToGame: (Int) -> Unit
) {
    composable(route = GamesNavigationDestination.route) {
        GamesRoute(
            navigateToNewGame = navigateToNewGame,
            navigateToGame = navigateToGame
        )
    }
}