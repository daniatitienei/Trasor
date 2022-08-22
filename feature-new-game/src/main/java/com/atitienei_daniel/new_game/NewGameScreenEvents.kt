package com.atitienei_daniel.new_game

import com.atitienei_daniel.core_model.Player

sealed interface NewGameScreenEvents {
    data class OnNewPlayerNameChanged(val value: String) : NewGameScreenEvents
    data class OnGameNameChanged(val value: String) : NewGameScreenEvents
    data class OnMaxPointsChanged(val value: String) : NewGameScreenEvents
    object OnSaveNewPlayer : NewGameScreenEvents
    object OnCreateGame : NewGameScreenEvents
    data class OnRemovePlayerClick(val player: Player) : NewGameScreenEvents
}