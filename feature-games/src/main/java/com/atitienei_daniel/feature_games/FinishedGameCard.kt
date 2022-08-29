@file:OptIn(ExperimentalMaterial3Api::class)

package com.atitienei_daniel.feature_games

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.feature_games.R

@Composable
fun FinishedGameCard(
    game: Game
) {
    Card(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = game.name,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.winner) + " \uD83D\uDC51",
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            style = MaterialTheme.typography.titleLarge,
        )
        ListItem(
            headlineText = {
                Text(text = game.winner?.name.toString())
            },
            trailingContent = {
                Text(
                    text = "${game.winner?.score} points",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.players),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        game.players.forEach { player ->
            ListItem(
                headlineText = {
                    Text(text = player.name)
                },
                trailingContent = {
                    Text(
                        text = "${player.score} points",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                )
            )
        }
    }
}