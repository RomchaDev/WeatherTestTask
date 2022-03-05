package com.example.layer_presentation.core.main

/**
 * Represents all possible states of ui.
 * */
sealed class AppState<out T : AppStateEntity> {
    data class Success<out T : AppStateEntity>(val data: T) : AppState<T>()
    data class Error<out T : AppStateEntity>(val error: Throwable) : AppState<T>()
    data class Message<out T : AppStateEntity>(val message: String) : AppState<T>()
    class Loading<out T : AppStateEntity> : AppState<T>()
}