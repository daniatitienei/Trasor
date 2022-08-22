package com.atitienei_daniel.new_game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atitienei_daniel.core_data.repository.GameRepository
import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewGameViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(NewGameScreenState())
    val uiState = _uiState.asStateFlow()
    fun onEvent(event: NewGameScreenEvents) {
        when (event) {
            is NewGameScreenEvents.OnNewPlayerNameChanged -> {
                _uiState.value = _uiState.value.copy(
                    newPlayerName = event.value
                )
            }
            is NewGameScreenEvents.OnGameNameChanged -> {
                _uiState.value = _uiState.value.copy(
                    gameName = event.value
                )
            }
            is NewGameScreenEvents.OnMaxPointsChanged -> {
                _uiState.value = _uiState.value.copy(
                    maxPoints = event.value
                )
            }
            is NewGameScreenEvents.OnSaveNewPlayer -> {
                val newPlayersList = _uiState.value.players.toMutableList()

                newPlayersList.add(
                    Player(name = _uiState.value.newPlayerName)
                )

                _uiState.value = _uiState.value.copy(
                    players = newPlayersList,
                    newPlayerName = ""
                )
            }
            is NewGameScreenEvents.OnCreateGame -> {
                viewModelScope.launch {
                    gameRepository.insertGame(
                        game = Game(
                            name = _uiState.value.gameName,
                            players = _uiState.value.players
                        )
                    )
                }
            }
            is NewGameScreenEvents.OnRemovePlayerClick -> {
                val newPlayersList = _uiState.value.players.toMutableList()

                newPlayersList.remove(event.player)

                _uiState.value = _uiState.value.copy(
                    players = newPlayersList
                )
            }
        }
    }

    fun clearNewPlayerName() {
        _uiState.value = _uiState.value.copy(
            newPlayerName = ""
        )
    }
}