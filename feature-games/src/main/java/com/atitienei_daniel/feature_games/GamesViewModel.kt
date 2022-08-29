package com.atitienei_daniel.feature_games

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atitienei_daniel.core_data.repository.GameRepository
import com.atitienei_daniel.core_datastore.LatestGameDataStore
import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_navigation.NewGameNavigationDestination
import com.atitienei_daniel.core_navigation.TrasorNavigationDestination
import com.atitienei_daniel.core_navigation.UiEvent
import com.atitienei_daniel.core_navigation.UpdateGameNavigationDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val latestGameDataStore: LatestGameDataStore
) : ViewModel() {

    val games = gameRepository.getGamesStream().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )

    val latestGame = latestGameDataStore.latestGameStream
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var _uiState = MutableStateFlow(GamesScreenState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: GameScreenEvents) {
        when (event) {
            is GameScreenEvents.OnCreateGameFABClick -> {
                sendEvent(UiEvent.Navigate(route = NewGameNavigationDestination.route))
            }
            is GameScreenEvents.OnGameCardClick -> {
                sendEvent(
                    UiEvent.Navigate(
                        route = UpdateGameNavigationDestination.route.replace(
                            "{gameId}",
                            event.gameId.toString()
                        )
                    )
                )
            }
            is GameScreenEvents.ToggleFinishedGamesCard -> {
                _uiState.value = _uiState.value.copy(
                    isFinishedGamesCardExpanded = !_uiState.value.isFinishedGamesCardExpanded
                )
            }
        }
    }

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}