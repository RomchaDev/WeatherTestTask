package com.example.layer_presentation.core.navigation

/**
 * Class for handling result from previous fragment
 * */
interface NavigationResultListener<T> {
    fun onNavigationResult(result: T?)
}