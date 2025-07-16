package com.madrid.presentation.screens.searchScreen.viewModel.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S>(initialState: S) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    protected fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: ((T) -> Unit)? = null,
        onError: (Throwable) -> Unit,
        scope: CoroutineScope = viewModelScope,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Job = runWithErrorHandling(onError, scope, dispatcher) {
        function().let { result ->
            onSuccess?.invoke(result)
        }
    }

    protected fun <T> tryToCollect(
        function: suspend () -> Flow<T>,
        onNewValue: suspend (T) -> Unit,
        onError: (Throwable) -> Unit,
        scope: CoroutineScope = viewModelScope,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Job =
        runWithErrorHandling(onError, scope, dispatcher) {
            function().distinctUntilChanged().collectLatest {
                onNewValue(it)
            }
        }

    protected fun updateState(updater: (S) -> S) {
        _state.update(updater)
    }

    private fun runWithErrorHandling(
        onError: (Throwable) -> Unit,
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher,
        function: suspend () -> Unit,
    ): Job {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            onError(throwable)
        }
        return scope.launch(dispatcher + coroutineExceptionHandler) {
            function()
        }
    }
}