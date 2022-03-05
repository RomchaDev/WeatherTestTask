package com.example.layer_presentation.core.view

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope, block: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        collect { t ->
            block(t)
        }
    }
}