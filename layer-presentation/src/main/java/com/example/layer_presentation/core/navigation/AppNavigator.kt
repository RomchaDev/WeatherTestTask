package com.example.layer_presentation.core.navigation

import android.os.Bundle

interface AppNavigator {
    /**
     * Navigates to the another fragment by command
     * */
    fun navigate(command: NavigationCommand, arguments: Bundle? = null)

    /**
     * Method that returns data to the next fragment after current one is closed.
     * */
    fun <T>setResult(key: String, value: T)

    /**
     * Subscribes to result that is passed in the next fragment.
     * */
    fun <T>subscribeToResult(listener: NavigationResultListener<T>, key: String)

    /**
     * Returns to the previous fragment
     * */
    fun back()

    /**
     * Method that should be called when navigator is going to be destroyed
     * */
    fun onDestroyNavigation()
}