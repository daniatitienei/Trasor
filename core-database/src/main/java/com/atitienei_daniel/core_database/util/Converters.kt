package com.atitienei_daniel.core_database.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.atitienei_daniel.core_database.model.PlayerEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class PlayerEntityConverter(
    private val moshi: Moshi
) {
    private val playerAdapter = moshi.adapter(PlayerEntity::class.java)

    @TypeConverter
    fun stringToPlayerEntity(value: String?): PlayerEntity? =
        value?.let { playerAdapter.fromJson(it) }

    @TypeConverter
    fun playerEntityToString(value: PlayerEntity?): String? =
        playerAdapter.toJson(value)
}

@ProvidedTypeConverter
class PlayerEntityListConverter(
    private val moshi: Moshi
) {
    private val playerList = Types.newParameterizedType(List::class.java, PlayerEntity::class.java)
    private val playerListAdapter = moshi.adapter<List<PlayerEntity>>(playerList)

    @TypeConverter
    fun stringToPlayerEntityList(value: String): List<PlayerEntity>? =
        playerListAdapter.fromJson(value)

    @TypeConverter
    fun playerEntityListToString(value: List<PlayerEntity>): String? =
        playerListAdapter.toJson(value)
}