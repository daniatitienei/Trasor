package com.atitienei_daniel.core_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.atitienei_daniel.core_database.model.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getGamesEntitiesStream(): Flow<List<GameEntity>>

    @Query(
        """
        SELECT * FROM game
        WHERE id = :gameId
    """
    )
    fun getGameEntityStream(gameId: Int): Flow<GameEntity>

    @Insert
    suspend fun insertGame(gameEntity: GameEntity)

    @Update
    suspend fun updateGame(gameEntity: GameEntity)

    @Delete
    suspend fun deleteGame(gameId: Int)
}