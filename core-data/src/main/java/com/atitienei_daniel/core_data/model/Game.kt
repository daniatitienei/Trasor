package com.atitienei_daniel.core_data.model

import com.atitienei_daniel.core_database.model.GameEntity
import com.atitienei_daniel.core_model.Game

fun Game.asEntity() = GameEntity(
    id = id,
    name = name,
    players = players.map { it.asEntity() },
    finished = finished,
    winner = winner?.asEntity()
)