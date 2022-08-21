package com.atitienei_daniel.core_data.repository

import com.atitienei_daniel.core_data.model.asEntity
import com.atitienei_daniel.core_database.dao.GameDao
import com.atitienei_daniel.core_database.model.GameEntity
import com.atitienei_daniel.core_database.model.asExternalModel
import com.atitienei_daniel.core_model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameDao: GameDao
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
}