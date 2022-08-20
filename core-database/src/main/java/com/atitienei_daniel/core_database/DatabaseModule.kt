package com.atitienei_daniel.core_database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesGameDatabase(
        @ApplicationContext context: Context
    ): GameDatabase = Room.databaseBuilder(
        context,
        GameDatabase::class.java,
        "game-database"
    ).build()
}