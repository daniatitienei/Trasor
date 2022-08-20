package com.atitienei_daniel.core_database

import com.atitienei_daniel.core_database.dao.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    @Singleton
    fun providesGameDao(
        database: GameDatabase
    ): GameDao = database.gameDao()
}