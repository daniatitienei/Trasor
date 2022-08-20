package com.atitienei_daniel.core_data.repository

import com.atitienei_daniel.core_model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGamesStream(): Flow<List<Game>>
    fun getGameStream(gameId: Int): Flow<Game>
    suspend fun insertGame(game: Game)
    suspend fun updateGame(game: Game)
    suspend fun deleteGame(gameId: Int)
}