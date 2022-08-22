package com.atitienei_daniel.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.atitienei_daniel.core_database.dao.GameDao
import com.atitienei_daniel.core_database.model.GameEntity
import com.atitienei_daniel.core_database.util.PlayerEntityConverter
import com.atitienei_daniel.core_database.util.PlayerEntityListConverter

@Database(
    entities = [GameEntity::class],
    version = 2
)
@TypeConverters(
    PlayerEntityConverter::class,
    PlayerEntityListConverter::class
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}