package com.example.weathertesttask.navigation

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.example.weathertesttask.navigation.AndroidNavigator
import org.koin.android.ext.android.inject

abstract class NavigationContainerActivity(
    private val hostFragmentId: Int,
) : AppCompatActivity() {
    private val navigator : AndroidNavigator by inject()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigator.navHostActivity = this
        navigator.hostFragmentId = hostFragmentId
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        navigator.onDestroyNavigation()
    }
}