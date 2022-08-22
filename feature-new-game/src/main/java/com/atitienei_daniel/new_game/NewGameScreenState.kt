package com.atitienei_daniel.new_game

import com.atitienei_daniel.core_model.Player

data class NewGameScreenState(
    val gameName: String = "",
    val maxPoints: String = "",
    val newPlayerName: String = "",
    val players: List<Player> = emptyList()
)
