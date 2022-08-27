@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalLifecycleComposeApi::class
)

package com.atitienei_daniel.new_game

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atitienei_daniel.core_designsystem.theme.TrasorTheme
import com.atitienei_daniel.core_model.Player
import com.atitienei_daniel.feature_new_game.R
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.launch

@Composable
fun NewGameRoute(
    onBackClick: () -> Unit,
    viewModel: NewGameViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val focusManager = LocalFocusManager.current
    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(
        key1 = modalBottomSheetState.currentValue,
        block = {
            if (modalBottomSheetState.currentValue == ModalBottomSheetValue.Expanded) {
                focusRequester.requestFocus()
            } else {
                viewModel.clearNewPlayerName()
                focusRequester.freeFocus()
                focusManager.clearFocus(force = true)
            }
        }
    )

    NewGameScreen(
        onBackClick = onBackClick,
        modalBottomSheetState = modalBottomSheetState,
        uiState = uiState,
        onEvent = viewModel::onEvent,
        focusRequester = focusRequester,
        focusManager = focusManager
    )
}

@Composable
fun NewGameScreen(
    onBackClick: () -> Unit,
    modalBottomSheetState: ModalBottomSheetState,
    uiState: NewGameScreenState,
    onEvent: (NewGameScreenEvents) -> Unit,
    focusRequester: FocusRequester,
    focusManager: FocusManager
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    AddPlayerModalBottomSheet(
        playerName = uiState.newPlayerName,
        playerNameError = uiState.newPlayerNameError,
        onEvent = onEvent,
        focusRequester = focusRequester,
        modalBottomSheetState = modalBottomSheetState
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.new_game))
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
                    text = {
                        Text(text = stringResource(id = R.string.create_game))
                    },
                    icon = {
                        Icon(imageVector = Icons.Rounded.Create, contentDescription = null)
                    },
                    onClick = {
                        onEvent(
                            NewGameScreenEvents.OnCreateGame(
                                onSuccess = onBackClick
                            )
                        )
                    }
                )
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    top = 15.dp,
                    bottom = innerPadding.calculateTopPadding() + 15.dp
                ),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                item {
                    Text(
                        text = stringResource(R.string.game_name),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier.animateContentSize(tween(200))
                    ) {
                        OutlinedTextField(
                            value = uiState.gameName,
                            onValueChange = {
                                onEvent(NewGameScreenEvents.OnGameNameChanged(it))
                            },
                            placeholder = {
                                Text(text = stringResource(R.string.game_name_placeholder))
                            },
                            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                                capitalization = KeyboardCapitalization.Sentences
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            ),
                            singleLine = true,
                            isError = uiState.gameNameError != null
                        )
                        uiState.gameNameError?.let { errorMessage ->
                            Text(
                                text = errorMessage,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
                item {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = MaterialTheme.typography.titleLarge.toSpanStyle()) {
                                append(context.getString(R.string.max_points))
                            }
                            append(" ")
                            withStyle(style = MaterialTheme.typography.bodyLarge.toSpanStyle()) {
                                append("(${context.getString(R.string.optional)})")
                            }
                        },
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = uiState.maxScore ?: "",
                        onValueChange = {
                            onEvent(NewGameScreenEvents.OnMaxPointsChanged(it))
                        },
                        placeholder = {
                            Text(text = stringResource(R.string.max_points_placeholder))
                        },
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true,
                        keyboardActions = KeyboardActions(
                            onDone = {
                                coroutineScope.launch {

                                    modalBottomSheetState.show()
                                }
                            }
                        )
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.players),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                item {
                    Players(
                        onAddNewPlayerClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.show()
                            }
                        },
                        onRemovePlayerClick = {
                            onEvent(NewGameScreenEvents.OnRemovePlayerClick(it))
                        },
                        players = uiState.players
                    )
                }
            }
        }
    }
}

@Composable
private fun Players(
    onAddNewPlayerClick: () -> Unit,
    onRemovePlayerClick: (Player) -> Unit,
    players: List<Player>
) {
    FlowRow(
        mainAxisSpacing = 8.dp,
        modifier = Modifier.animateContentSize()
    ) {
        players.forEach { player ->
            InputChip(
                selected = true,
                onClick = { /*TODO*/ },
                label = { Text(text = player.name) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onRemovePlayerClick(player)
                        }
                    )
                }
            )
        }
        InputChip(
            selected = true,
            onClick = onAddNewPlayerClick,
            label = { Text(text = stringResource(id = R.string.add_player)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                )
            },
            colors = InputChipDefaults.inputChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
}

@Composable
private fun AddPlayerModalBottomSheet(
    playerName: String,
    playerNameError: String?,
    onEvent: (NewGameScreenEvents) -> Unit,
    focusRequester: FocusRequester,
    modalBottomSheetState: ModalBottomSheetState,
    content: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .animateContentSize(tween(200))
            ) {
                TextField(
                    value = playerName,
                    onValueChange = {
                        onEvent(NewGameScreenEvents.OnNewPlayerNameChanged(it))
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.player_name))
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onEvent(NewGameScreenEvents.OnSaveNewPlayer)
                        }
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        placeholderColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                    ),
                    isError = playerNameError != null
                )
                playerNameError?.let { errorMessage ->
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    TextButton(
                        onClick = {
                            onEvent(NewGameScreenEvents.OnSaveNewPlayer)
                        }
                    ) {
                        Text(text = stringResource(id = R.string.save))
                    }
                }
            }
        },
        sheetElevation = 0.dp,
        content = content
    )
}

@Preview
@Composable
fun NewGameScreenPreview() {
    TrasorTheme {
        NewGameScreen(
            onBackClick = {},
            modalBottomSheetState = ModalBottomSheetState(ModalBottomSheetValue.Hidden),
            uiState = NewGameScreenState(),
            onEvent = {},
            focusRequester = FocusRequester(),
            focusManager = LocalFocusManager.current
        )
    }
}