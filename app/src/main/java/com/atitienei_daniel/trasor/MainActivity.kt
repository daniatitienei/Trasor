package com.atitienei_daniel.trasor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.atitienei_daniel.core_designsystem.theme.TrasorTheme
import com.atitienei_daniel.feature_games.GamesRoute
import com.atitienei_daniel.trasor.navigation.TrasorNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrasorTheme {
                TrasorNavHost()
            }
        }
    }
}