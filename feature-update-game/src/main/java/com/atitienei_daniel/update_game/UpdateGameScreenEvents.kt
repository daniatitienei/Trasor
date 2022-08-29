package com.atitienei_daniel.update_game

import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_model.Player

sealed interface UpdateGameScreenEvents {
    data class OnIncreasePlayerScore(val player: Player) : UpdateGameScreenEvents
    data class OnDecreasePlayerScore(val player: Player) : UpdateGameScreenEvents
    object OnEndGame : UpdateGameScreenEvents

    object OnNavigateBack : UpdateGameScreenEvents
}