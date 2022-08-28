package com.atitienei_daniel.feature_games.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.atitienei_daniel.core_navigation.GamesNavigationDestination
import com.atitienei_daniel.core_navigation.TrasorNavigationDestination
import com.atitienei_daniel.feature_games.GamesRoute

fun NavGraphBuilder.gamesGraph(
    onNavigate: (String) -> Unit
) {
    composable(route = GamesNavigationDestination.route) {
        GamesRoute(
            onNavigate = onNavigate
        )
    }
}