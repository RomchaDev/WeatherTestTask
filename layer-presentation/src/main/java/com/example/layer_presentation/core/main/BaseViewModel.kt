package com.example.layer_presentation.core.main

import androidx.lifecycle.ViewModel
import com.example.layer_domain.entity.AppStateEntity
import com.example.layer_presentation.core.navigation.AppNavigator
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<D : AppStateEntity> : ViewModel() {

    protected abstract val navigator : AppNavigator

    //Used SharedFlow because StateFlow has replays only one action which can cause some problems.
    protected val mSharedFlow = MutableSharedFlow<AppState<D>>(
        replay = 2,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val stateLiveData get() = mSharedFlow.asSharedFlow()

    private val ioCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private val mainCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected open fun cancelJob() {
        ioCoroutineScope.coroutineContext.cancelChildren()
    }

    open fun handleError(error: Throwable) {
        error.printStackTrace()
        runAsync {
            mSharedFlow.emit(AppState.Error(error))
        }
    }

    open fun onViewInit() {
    }

    protected fun runAsync(block: suspend () -> Unit) =
        ioCoroutineScope.launch {
            block()
        }


    protected fun <T> runAsyncWithResult(block: suspend () -> T) =
        ioCoroutineScope.async {
            block()
        }

    protected fun runOnMainThread(block: suspend () -> Unit) =
        mainCoroutineScope.launch {
            block()
        }
}
