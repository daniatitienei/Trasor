package com.atitienei_daniel.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atitienei_daniel.core_database.dao.GameDao
import com.atitienei_daniel.core_database.model.GameEntity
import com.atitienei_daniel.core_database.model.PlayerEntity

@Database(
    entities = [PlayerEntity::class, GameEntity::class],
    version = 1
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}