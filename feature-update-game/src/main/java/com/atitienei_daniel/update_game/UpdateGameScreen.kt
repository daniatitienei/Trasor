@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)

package com.atitienei_daniel.update_game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atitienei_daniel.core_designsystem.theme.TrasorTheme
import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_model.previewGame
import com.example.update_game.R

@Composable
fun UpdateGameRoute(
    onBackClick: () -> Unit,
    viewModel: UpdateGameViewModel = hiltViewModel(),
) {
    val game by viewModel.game.collectAsStateWithLifecycle(Game())

    UpdateGameScreen(
        game = game,
        onEvent = viewModel::onEvent,
        onBackClick = onBackClick
    )
}

@Composable
fun UpdateGameScreen(
    game: Game,
    onEvent: (UpdateGameScreenEvents) -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = game.name)
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
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
                    onEvent(UpdateGameScreenEvents.OnEndGame(game))
                    onBackClick()
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
                Text(
                    text = stringResource(id = R.string.players),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            items(game.players) { player ->
                PlayerCard(
                    player = player,
                    increaseScore = {
                        onEvent(UpdateGameScreenEvents.OnIncreasePlayerScore(game, player))
                    },
                    decreaseScore = {
                        onEvent(UpdateGameScreenEvents.OnDecreasePlayerScore(game, player))
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
        UpdateGameScreen(game = previewGame, onEvent = {}, onBackClick = {})
    }
}