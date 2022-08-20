@file:OptIn(ExperimentalMaterial3Api::class)

package com.atitienei_daniel.feature_games

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.atitienei_daniel.core_designsystem.theme.TrasorTheme
import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_model.Player
import com.atitienei_daniel.core_model.previewGame

@Composable
fun UnfinishedGameCard(game: Game) {
    Card(
        onClick = { /*TODO*/ }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = game.name,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        game.players.forEach { player ->
            ListItem(
                headlineText = {
                    Text(text = player.name)
                },
                leadingContent = {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(player.color)
                    )
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

@Preview(showBackground = true)
@Composable
fun UnfinishedGameCardPreview() {
    TrasorTheme {
        UnfinishedGameCard(
            game = previewGame
        )
    }
}