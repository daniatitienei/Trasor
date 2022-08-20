@file:OptIn(ExperimentalMaterial3Api::class)

package com.atitienei_daniel.core_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.atitienei_daniel.core_designsystem.theme.TrasorTheme
import com.atitienei_daniel.core_model.Player
import com.atitienei_daniel.core_model.previewPlayer

@Composable
fun WinnerCard(player: Player) {
    Card {
        Text(
            text = stringResource(id = R.string.winner) + " \uD83D\uDC51",
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        )
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
                Text(text = "${player.score} points", style = MaterialTheme.typography.titleMedium)
            },
            colors = ListItemDefaults.colors(
                containerColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WinnerCardPreview() {
    TrasorTheme {
        WinnerCard(
            player = previewPlayer
        )
    }
}