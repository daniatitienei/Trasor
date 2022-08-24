package com.atitienei_daniel.core_datastore.di

import android.content.Context
import com.atitienei_daniel.core_datastore.LatestGameDataStore
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesLatestGameDataStore(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): LatestGameDataStore = LatestGameDataStore(
        context = context,
        moshi = moshi
    )
}

