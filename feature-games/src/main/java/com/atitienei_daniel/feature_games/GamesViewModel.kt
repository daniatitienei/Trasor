package com.atitienei_daniel.feature_games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atitienei_daniel.core_data.repository.GameRepository
import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_model.previewGame
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    val games = gameRepository.getGamesStream()


}