package com.atitienei_daniel.core_data.di

import com.atitienei_daniel.core_data.repository.GameRepository
import com.atitienei_daniel.core_data.repository.GameRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsGameRepository(
        gameRepository: GameRepositoryImpl
    ): GameRepository
}