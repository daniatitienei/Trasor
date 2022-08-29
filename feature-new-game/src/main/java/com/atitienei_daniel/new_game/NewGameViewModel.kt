package com.atitienei_daniel.new_game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atitienei_daniel.core_data.repository.GameRepository
import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_model.Player
import com.atitienei_daniel.core_navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewGameViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(NewGameScreenState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

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
                    maxScore = event.value
                )
            }
            is NewGameScreenEvents.OnSaveNewPlayer -> {
                if (_uiState.value.newPlayerName.isEmpty()) {
                    _uiState.value = _uiState.value.copy(
                        newPlayerNameError = "Name is empty"
                    )
                    return
                }

                clearPlayerNameError()

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
                if (_uiState.value.gameName.isEmpty()) {
                    _uiState.value = _uiState.value.copy(
                        gameNameError = "Name is empty"
                    )
                    return
                }

                clearGameNameError()

                val hasError = listOf(
                    _uiState.value.gameNameError,
                    _uiState.value.newPlayerNameError
                ).any { it != null }

                if (!hasError && _uiState.value.players.isNotEmpty()) {
                    viewModelScope.launch {
                        gameRepository.insertGame(
                            game = Game(
                                name = _uiState.value.gameName,
                                players = _uiState.value.players,
                                maxScore = _uiState.value.maxScore?.toInt()
                            )
                        )
                    }
                    sendEvent(UiEvent.PopBackStack)
                }
            }
            is NewGameScreenEvents.OnRemovePlayerClick -> {
                val newPlayersList = _uiState.value.players.toMutableList()

                newPlayersList.remove(event.player)

                _uiState.value = _uiState.value.copy(
                    players = newPlayersList
                )
            }
            is NewGameScreenEvents.OnNavigateBack -> {
                sendEvent(UiEvent.PopBackStack)
            }
        }
    }

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun clearNewPlayerName() {
        _uiState.value = _uiState.value.copy(
            newPlayerName = ""
        )
    }

    fun clearPlayerNameError() {
        _uiState.value = _uiState.value.copy(
            newPlayerNameError = null
        )
    }

    fun clearGameNameError() {
        _uiState.value = _uiState.value.copy(
            gameNameError = null
        )
    }
}