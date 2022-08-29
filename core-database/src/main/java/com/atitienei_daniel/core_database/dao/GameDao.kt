package com.atitienei_daniel.core_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.atitienei_daniel.core_database.model.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM GAME")
    fun getGamesEntitiesStream(): Flow<List<GameEntity>>

    @Query(
        """
            SELECT * FROM GAME
            WHERE id = :gameId
        """
    )
    fun getGameEntityStream(gameId: Int): Flow<GameEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreGame(gameEntity: GameEntity)

    @Update
    suspend fun updateGame(gameEntity: GameEntity)

    @Query(
        value = """
            DELETE FROM GAME
            WHERE id = :gameId
        """
    )
    suspend fun deleteGame(gameId: Int)
}