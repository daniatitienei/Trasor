package com.atitienei_daniel.core_database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.atitienei_daniel.core_model.Game

@Entity(
    tableName = "game"
)
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val players: List<PlayerEntity>,
    val maxScore: Int?,
    val finished: Boolean,
    val winner: PlayerEntity?
)

fun GameEntity.asExternalModel() = Game(
    id = id,
    maxScore = maxScore,
    name = name,
    players = players.map { it.toExternalModel() },
    finished = finished,
    winner = winner?.toExternalModel()
)
