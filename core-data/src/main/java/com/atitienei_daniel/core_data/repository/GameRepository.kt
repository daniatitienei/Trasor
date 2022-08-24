package com.atitienei_daniel.core_data.repository

import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_model.Player
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGamesStream(): Flow<List<Game>>
    fun getGameStream(gameId: Int): Flow<Game>
    suspend fun insertGame(game: Game)
    suspend fun updateGame(game: Game)
    suspend fun deleteGame(gameId: Int)

    suspend fun increasePlayerScore(game: Game, player: Player)
    suspend fun decreasePlayerScore(game: Game, player: Player)

    suspend fun endGame(game: Game)
}