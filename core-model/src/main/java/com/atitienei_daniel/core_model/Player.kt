package com.atitienei_daniel.core_model

import androidx.compose.ui.graphics.Color

data class Player(
    val name: String = "",
    val color: Color = Color.LightGray,
    val score: Int = 0
)

val previewPlayer = Player(
    name = "Dani",
    score = 13,
    color = Color.Green
)
