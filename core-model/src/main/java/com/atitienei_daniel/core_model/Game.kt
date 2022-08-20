package com.atitienei_daniel.core_model

import androidx.compose.ui.graphics.Color

data class Game(
    val id: Int = 0,
    val name: String = "",
    val players: List<Player> = emptyList(),
    val finished: Boolean = false,
    val winner: Player? = null
)

val previewGame = Game(
    name = "Cruce",
    players = listOf(
        Player(
            name = "Dani",
            score = 13,
            color = Color.Green
        ),
        Player(
            name = "Hubuca",
            score = 8,
            color = Color.Magenta
        ),
        Player(
            name = "Vane",
            score = 3,
            color = Color.Yellow
        )
    )
)