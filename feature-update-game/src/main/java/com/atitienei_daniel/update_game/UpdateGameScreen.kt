@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)

package com.atitienei_daniel.update_game

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.atitienei_daniel.core_navigation.UiEvent
import com.atitienei_daniel.core_ui.WinnerCard
import com.atitienei_daniel.feature_update_game.R
import kotlinx.coroutines.flow.collect

@Composable
fun UpdateGameRoute(
    onBackClick: () -> Unit,
    viewModel: UpdateGameViewModel = hiltViewModel(),
) {
    val game by viewModel.game.collectAsStateWithLifecycle(Game())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.PopBackStack -> onBackClick()
                else -> Unit
            }
        }
    }

    UpdateGameScreen(
        game = game,
        onEvent = viewModel::onEvent,
    )
}

@Composable
fun UpdateGameScreen(
    game: Game,
    onEvent: (UpdateGameScreenEvents) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = game.name)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    onEvent(UpdateGameScreenEvents.OnEndGame)
                },
                text = {
                    Text(text = stringResource(id = R.string.end_game))
                },
                icon = {
                    Icon(imageVector = Icons.Rounded.Stop, contentDescription = null)
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                bottom = 15.dp + innerPadding.calculateBottomPadding(),
                top = 15.dp + innerPadding.calculateTopPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                AnimatedVisibility(
                    visible = game.winner != null,
                    enter = fadeIn(tween(200)),
                    exit = fadeOut(tween(200)),
                ) {
                    WinnerCard(player = game.winner!!)
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.players),
                        style = MaterialTheme.typography.titleLarge
                    )
                    game.maxScore?.let {
                        Text(text = game.maxScore.toString() + " " + stringResource(id = R.string.points))
                    }
                }
            }
            items(game.players) { player ->
                PlayerCard(
                    player = player,
                    increaseScore = {
                        onEvent(UpdateGameScreenEvents.OnIncreasePlayerScore(player))
                    },
                    decreaseScore = {
                        onEvent(UpdateGameScreenEvents.OnDecreasePlayerScore(player))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UpdateGameScreenPreview() {
    TrasorTheme {
        UpdateGameScreen(game = previewGame, onEvent = {})
    }
}