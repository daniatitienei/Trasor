package com.atitienei_daniel.feature_games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atitienei_daniel.core_data.repository.GameRepository
import com.atitienei_daniel.core_datastore.LatestGameDataStore
import com.atitienei_daniel.core_navigation.NewGameNavigationDestination
import com.atitienei_daniel.core_navigation.TrasorNavigationDestination
import com.atitienei_daniel.core_navigation.UiEvent
import com.atitienei_daniel.core_navigation.UpdateGameNavigationDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val latestGameDataStore: LatestGameDataStore
) : ViewModel() {

    val games = gameRepository.getGamesStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val latestGame = latestGameDataStore.latestGameStream
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.consumeAsFlow()

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
        }
    }

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}