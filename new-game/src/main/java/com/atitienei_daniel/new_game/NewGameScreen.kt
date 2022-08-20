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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.atitienei_daniel.core_designsystem.theme.TrasorTheme

@Composable
fun NewGameDestination() {
    NewGameScreen()
}

@Composable
fun NewGameScreen() {

    val context = LocalContext.current

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
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
        },
        sheetBackgroundColor = Color.Transparent,
        sheetElevation = 0.dp,
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.new_game))
                    },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
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
                        Text(text = stringResource(id = R.string.add_player))
                    },
                    icon = {
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
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
                items(5) {
                    ListItem(
                        headlineText = {
                            Text(text = "Dani")
                        },
                        leadingContent = {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(Color.Green)
                            )
                        },
                        trailingContent = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = null
                                )
                            }
                        },
                        modifier = Modifier.clip(
                            RoundedCornerShape(
                                corner = CornerSize(10.dp)
                            )
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NewGameScreenPreview() {
    TrasorTheme {
        NewGameScreen()
    }
}