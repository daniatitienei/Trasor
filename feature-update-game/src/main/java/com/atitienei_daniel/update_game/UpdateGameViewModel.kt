package com.atitienei_daniel.update_game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atitienei_daniel.core_data.repository.GameRepository
import com.atitienei_daniel.core_model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateGameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    lateinit var game: Flow<Game>

    init {
        val gameId = savedStateHandle.get<String>("gameId")?.toInt()

        gameId?.let { id ->
            game = gameRepository.getGameStream(id)
        }
    }

    fun onEvent(event: UpdateGameScreenEvents) {
        when (event) {
            is UpdateGameScreenEvents.OnIncreasePlayerScore -> {
                viewModelScope.launch {
                    gameRepository.increasePlayerScore(event.game, event.player)
                }
            }
            is UpdateGameScreenEvents.OnDecreasePlayerScore -> {
                viewModelScope.launch {
                    gameRepository.decreasePlayerScore(event.game, event.player)
                }
            }
            is UpdateGameScreenEvents.OnEndGame -> {
                viewModelScope.launch {
                    gameRepository.endGame(event.game)
                }
            }
        }
    }
}