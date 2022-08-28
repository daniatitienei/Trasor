package com.atitienei_daniel.core_navigation

sealed interface UiEvent {
    data class Navigate(val route: String) : UiEvent
    object PopBackStack : UiEvent
}