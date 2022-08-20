package com.atitienei_daniel.core_database.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.atitienei_daniel.core_model.Player

@Entity(
    tableName = "player"
)
data class PlayerEntity(
    @PrimaryKey
    val name: String,
    val color: Color,
    val score: Int
)

fun PlayerEntity.toExternalModel() = Player(
    name, color, score
)
