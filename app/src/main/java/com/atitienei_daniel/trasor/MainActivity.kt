package com.atitienei_daniel.trasor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.atitienei_daniel.core_designsystem.theme.TrasorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrasorTheme {

            }
        }
    }
}