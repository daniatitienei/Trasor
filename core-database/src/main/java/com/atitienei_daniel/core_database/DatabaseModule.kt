package com.atitienei_daniel.core_database

import android.content.Context
import androidx.room.Room
import com.atitienei_daniel.core_database.util.PlayerEntityConverter
import com.atitienei_daniel.core_database.util.PlayerEntityListConverter
import com.squareup.moshi.Moshi
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
        @ApplicationContext context: Context,
        moshi: Moshi
    ): GameDatabase = Room.databaseBuilder(
        context,
        GameDatabase::class.java,
        "game-database"
    )
        .addTypeConverter(PlayerEntityConverter(moshi))
        .addTypeConverter(PlayerEntityListConverter(moshi))
        .fallbackToDestructiveMigration()
        .build()
}