@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalLayoutApi::class
)

package com.atitienei_daniel.new_game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.atitienei_daniel.core_designsystem.theme.TrasorTheme
import com.atitienei_daniel.core_model.previewPlayer
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.launch

@Composable
fun NewGameRoute(
    onBackClick: () -> Unit
) {
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    NewGameScreen(
        onBackClick = onBackClick,
        modalBottomSheetState = modalBottomSheetState
    )
}

@Composable
fun NewGameScreen(
    onBackClick: () -> Unit,
    modalBottomSheetState: ModalBottomSheetState
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(text = stringResource(id = R.string.player_name))
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        placeholderColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                    )
                )
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Save")
                    }
                }
            }
        },
        sheetElevation = 0.dp,
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
                    onClick = { /*TODO*/ }
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
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = {
                            Text(text = stringResource(R.string.game_name_placeholder))
                        },
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        modifier = Modifier.fillMaxWidth()
                    )
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
                        value = "",
                        onValueChange = {},
                        placeholder = {
                            Text(text = stringResource(R.string.max_points_placeholder))
                        },
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.players),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                item {
                    FlowRow(
                        mainAxisSpacing = 8.dp
                    ) {
                        (1..5).forEach { _ ->
                            InputChip(
                                selected = true,
                                onClick = { /*TODO*/ },
                                label = { Text(text = previewPlayer.name) },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Close,
                                        contentDescription = null
                                    )
                                }
                            )
                        }
                        InputChip(
                            selected = true,
                            onClick = {
                                coroutineScope.launch {
                                    modalBottomSheetState.show()
                                }
                            },
                            label = { Text(text = stringResource(id = R.string.add_player)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Add,
                                    contentDescription = null
                                )
                            },
                            colors = InputChipDefaults.inputChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AddPlayerModalBottomSheet() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(30.dp)
            .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        Scaffold(
            bottomBar = {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.done))
                }
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier.consumedWindowInsets(innerPadding),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                item {
                    Text(
                        text = stringResource(id = R.string.player_name),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = {
                            Text(text = stringResource(R.string.player_name_placeholder))
                        },
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.color),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color.Green)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NewGameScreenPreview() {
    TrasorTheme {
        NewGameScreen(
            onBackClick = {},
            modalBottomSheetState = ModalBottomSheetState(ModalBottomSheetValue.Hidden)
        )
    }
}