package com.atitienei_daniel.update_game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atitienei_daniel.core_data.repository.GameRepository
import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateGameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    lateinit var game: StateFlow<Game>

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val gameId = savedStateHandle.get<String>("gameId")?.toInt()

        gameId?.let { id ->
            game = gameRepository.getGameStream(id).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1_000),
                initialValue = Game()
            )
        }
    }

    fun onEvent(event: UpdateGameScreenEvents) {
        when (event) {
            is UpdateGameScreenEvents.OnIncreasePlayerScore -> {
                viewModelScope.launch {
                    game.value.maxScore?.let { maxScore ->
                        if (event.player.score < maxScore) {
                            gameRepository.increasePlayerScore(game.value, event.player)
                        } else if (game.value.winner == null && event.player.score == maxScore) {
                            gameRepository.updateGame(game.value.copy(winner = event.player))
                        }
                    }
                    if (game.value.maxScore == null) {
                        gameRepository.increasePlayerScore(game.value, event.player)
                    }
                }
            }
            is UpdateGameScreenEvents.OnDecreasePlayerScore -> {
                viewModelScope.launch {
                    gameRepository.decreasePlayerScore(game.value, event.player)
                }
            }
            is UpdateGameScreenEvents.OnEndGame -> {
                viewModelScope.launch {
                    gameRepository.endGame(game.value)
                }
                sendEvent(UiEvent.PopBackStack)
            }
            is UpdateGameScreenEvents.OnNavigateBack -> {
                sendEvent(UiEvent.PopBackStack)
            }
        }
    }

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}