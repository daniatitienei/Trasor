package com.atitienei_daniel.new_game

import com.atitienei_daniel.core_model.Player

data class NewGameScreenState(
    val gameName: String = "",
    val gameNameError: String? = null,
    val maxScore: String? = null,
    val newPlayerName: String = "",
    val newPlayerNameError: String? = null,
    val players: List<Player> = emptyList()
)
