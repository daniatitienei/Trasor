package com.atitienei_daniel.new_game.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.atitienei_daniel.core_navigation.TrasorNavigationDestination
import com.atitienei_daniel.new_game.NewGameRoute

object NewGameNavigationDestination : TrasorNavigationDestination {
    override val route: String = "new_game_route"
}

fun NavGraphBuilder.newGameGraph(
    onBackClick: () -> Unit
) {
    composable(route = NewGameNavigationDestination.route) {
        NewGameRoute(onBackClick)
    }
}