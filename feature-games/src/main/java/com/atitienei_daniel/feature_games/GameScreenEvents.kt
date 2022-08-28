package com.atitienei_daniel.feature_games

sealed interface GameScreenEvents {
    data class OnGameCardClick(val gameId: Int) : GameScreenEvents
    object OnCreateGameFABClick : GameScreenEvents
}