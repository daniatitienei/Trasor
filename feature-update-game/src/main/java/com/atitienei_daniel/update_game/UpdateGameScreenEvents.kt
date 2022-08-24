package com.atitienei_daniel.update_game

import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_model.Player

sealed interface UpdateGameScreenEvents {
    data class OnIncreasePlayerScore(val game: Game, val player: Player) : UpdateGameScreenEvents
    data class OnDecreasePlayerScore(val game: Game, val player: Player) : UpdateGameScreenEvents

    data class OnEndGame(val game: Game) : UpdateGameScreenEvents
}