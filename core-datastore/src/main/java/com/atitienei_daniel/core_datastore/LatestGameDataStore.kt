package com.atitienei_daniel.core_datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.atitienei_daniel.core_model.Game
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LatestGameDataStore constructor(
    private val context: Context,
    private val moshi: Moshi
) {
    private val gameJsonAdapter = moshi.adapter(Game::class.java)

    private val Context.dataStore by preferencesDataStore(name = "latest_game")

    val latestGameStream: Flow<Game?> = context.dataStore.data
        .map { preferences ->
            preferences[latestGameKey]?.let { gameJsonAdapter.fromJson(it) }
                ?: Game()
        }

    suspend fun updateLatestGame(game: Game) = context.dataStore
        .edit { settings ->
            settings[latestGameKey] = gameJsonAdapter.toJson(game)
        }

    companion object {
        private val latestGameKey = stringPreferencesKey("latest_game_key")
    }
}