package com.atitienei_daniel.core_data.model

import com.atitienei_daniel.core_database.model.PlayerEntity
import com.atitienei_daniel.core_model.Player

fun Player.asEntity() = PlayerEntity(
    name, score
)