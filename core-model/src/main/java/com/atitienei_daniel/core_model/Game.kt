package com.atitienei_daniel.core_model

import androidx.compose.ui.graphics.Color

data class Game(
    val id: Int = 0,
    val name: String = "",
    val players: List<Player> = emptyList(),
    val finished: Boolean = false,
    val maxScore: Int? = null,
    val winner: Player? = null
)

val previewGame = Game(
    name = "Cruce",
    players = listOf(
        Player(
            name = "Dani",
            score = 13,
        ),
        Player(
            name = "Hubuca",
            score = 8,
        ),
        Player(
            name = "Vane",
            score = 3,
        )
    )
)