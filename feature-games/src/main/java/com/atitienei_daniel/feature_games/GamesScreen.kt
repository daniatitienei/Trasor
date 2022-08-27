@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalLifecycleComposeApi::class
)

package com.atitienei_daniel.feature_games

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atitienei_daniel.core_designsystem.theme.TrasorTheme
import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_model.previewGame
import com.atitienei_daniel.core_ui.WinnerCard

@Composable
fun GamesRoute(
    viewModel: GamesViewModel = hiltViewModel(),
    navigateToNewGame: () -> Unit,
    navigateToGame: (Int) -> Unit
) {
    val games by viewModel.games.collectAsStateWithLifecycle(initialValue = emptyList())
    val latestGame by viewModel.latestGame.collectAsStateWithLifecycle(initialValue = null)

    GamesScreen(
        games = games,
        latestGame = latestGame,
        navigateToNewGame = navigateToNewGame,
        navigateToUpdateGame = navigateToGame
    )
}

@Composable
fun GamesScreen(
    games: List<Game>,
    latestGame: Game?,
    navigateToNewGame: () -> Unit,
    navigateToUpdateGame: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = stringResource(id = R.string.new_game))
                },
                icon = {
                    Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                },
                onClick = navigateToNewGame
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                AnimatedVisibility(
                    visible = latestGame?.winner != null,
                    enter = fadeIn(tween(200)),
                    exit = fadeOut(tween(200))
                ) {
                    Text(
                        text = stringResource(R.string.latest_game),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            item {
                latestGame?.winner?.let { winner ->
                    WinnerCard(
                        player = winner
                    )
                }
            }
            item {
                AnimatedVisibility(
                    visible = games.isNotEmpty(),
                    enter = fadeIn(tween(200)),
                    exit = fadeOut(tween(200))
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = stringResource(id = R.string.unfinished_games) + " \uD83D\uDEA7",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            items(games) { game ->
                UnfinishedGameCard(
                    game = game,
                    onClick = {
                        navigateToUpdateGame(game.id)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun GamesScreenPreview() {
    TrasorTheme {
        GamesScreen(
            games = listOf(previewGame),
            latestGame = previewGame,
            navigateToNewGame = {},
            navigateToUpdateGame = {}
        )
    }
}