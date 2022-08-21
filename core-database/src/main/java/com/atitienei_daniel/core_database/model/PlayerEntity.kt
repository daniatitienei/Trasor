package com.atitienei_daniel.core_database.model

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.atitienei_daniel.core_model.Player
import com.squareup.moshi.JsonClass

@Entity(
    tableName = "player"
)
data class PlayerEntity(
    val name: String,
    val score: Int
)

fun PlayerEntity.toExternalModel() = Player(
    name, score
)
