package com.atitienei_daniel.core_data.repository

import com.atitienei_daniel.core_data.model.asEntity
import com.atitienei_daniel.core_database.dao.GameDao
import com.atitienei_daniel.core_database.model.GameEntity
import com.atitienei_daniel.core_database.model.asExternalModel
import com.atitienei_daniel.core_datastore.LatestGameDataStore
import com.atitienei_daniel.core_model.Game
import com.atitienei_daniel.core_model.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameDao: GameDao,
    private val latestGameDatastore: LatestGameDataStore
) : GameRepository {
    override fun getGamesStream(): Flow<List<Game>> =
        gameDao.getGamesEntitiesStream()
            .map { it.map(GameEntity::asExternalModel) }

    override fun getGameStream(id: Int): Flow<Game> =
        gameDao.getGameEntityStream(id).map {
            it.asExternalModel()
        }

    override suspend fun insertGame(game: Game) {
        gameDao.insertOrIgnoreGame(game.asEntity())
    }

    override suspend fun updateGame(game: Game) {
        gameDao.updateGame(game.asEntity())
    }

    override suspend fun deleteGame(gameId: Int) {
        gameDao.deleteGame(gameId)
    }

    override suspend fun increasePlayerScore(game: Game, player: Player) {
        val playerWithScoreUpdated = player.copy(
            score = player.score + 1
        )
        val players = game.players.toMutableList().map {
            if (it.name == player.name) {
                playerWithScoreUpdated
            } else it
        }

        updateGame(
            game.copy(
                players = players
            )
        )
    }

    override suspend fun decreasePlayerScore(game: Game, player: Player) {
        if (player.score < 1) {
            return
        }
        val playerWithScoreUpdated = player.copy(
            score = player.score - 1
        )
        val players = game.players.toMutableList().map {
            if (it.name == player.name) {
                playerWithScoreUpdated
            } else it
        }

        updateGame(
            game.copy(
                players = players
            )
        )
    }

    override suspend fun endGame(game: Game) {
        val winner = game.players.reduce { previousPlayer, player ->
            if (previousPlayer.score < player.score) {
                player
            } else {
                previousPlayer
            }
        }

        latestGameDatastore.updateLatestGame(
            game = game.copy(
                winner = winner
            )
        )
        deleteGame(game.id)
    }
}