@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLifecycleComposeApi::class
)

package com.atitienei_daniel.feature_games

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.atitienei_daniel.core_designsystem.theme.TrasorTheme
import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_model.previewGame
import com.atitienei_daniel.core_navigation.UiEvent
import com.atitienei_daniel.core_ui.WinnerCard
import kotlinx.coroutines.launch

@Composable
fun GamesRoute(
    viewModel: GamesViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val games by viewModel.games.collectAsStateWithLifecycle()
    val latestGame by viewModel.latestGame.collectAsStateWithLifecycle(null)

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }
                else -> Unit
            }
        }
    }

    GamesScreen(
        uiState = uiState,
        games = games,
        latestGame = latestGame,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun GamesScreen(
    uiState: GamesScreenState,
    games: List<Game>,
    latestGame: Game?,
    onEvent: (GameScreenEvents) -> Unit
) {
    val finishedGames = games.filter { it.winner != null }
    val unFinishedGames = games.filter { it.winner == null }

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
                onClick = {
                    onEvent(GameScreenEvents.OnCreateGameFABClick)
                }
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(tween(200))
                        .clickable {
                            onEvent(GameScreenEvents.ToggleFinishedGamesCard)
                        },
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = stringResource(id = R.string.finished_games),
                                style = MaterialTheme.typography.titleLarge,
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            IconButton(
                                onClick = { onEvent(GameScreenEvents.ToggleFinishedGamesCard) }
                            ) {
                                Icon(
                                    imageVector = if (!uiState.isFinishedGamesCardExpanded) Icons.Rounded.KeyboardArrowDown else Icons.Rounded.KeyboardArrowUp,
                                    contentDescription = null
                                )
                            }
                        }
                    }

                    if (uiState.isFinishedGamesCardExpanded) {
                        finishedGames.forEach {
                            FinishedGameCard(game = it)
                        }
                    }
                }
            }
            item {
                AnimatedVisibility(
                    visible = unFinishedGames.isNotEmpty(),
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
            items(unFinishedGames) { game ->
                UnfinishedGameCard(
                    game = game,
                    onClick = {
                        onEvent(GameScreenEvents.OnGameCardClick(game.id))
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
            uiState = GamesScreenState(),
            games = listOf(previewGame),
            latestGame = previewGame,
            onEvent = {}
        )
    }
}