package com.atitienei_daniel.trasor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.atitienei_daniel.core_designsystem.theme.TrasorTheme
import com.atitienei_daniel.feature_games.GamesScreen
import com.atitienei_daniel.new_game.NewGameDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrasorTheme {
                GamesScreen()
            }
        }
    }
}