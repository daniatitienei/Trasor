package com.atitienei_daniel.core_model

import androidx.compose.ui.graphics.Color

data class Player(
    val name: String = "",
    val score: Int = 0
)

val previewPlayer = Player(
    name = "Dani",
    score = 13,
)
