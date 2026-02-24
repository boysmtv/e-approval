/*
 * Project: E-Approval
 * Author: Boys.mtv@gmail.com
 * File: BaseViewModel.kt
 *
 * Last modified by Dedy Wijaya on 23/02/26 15.27
 */

package id.co.ikonsultan.approval.core.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.ikonsultan.approval.core.common.coroutine.DispatcherProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : UiState, EVENT : UiEvent, EFFECT : UiEffect>(
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    abstract fun createInitialState(): STATE

    private val _state = MutableStateFlow(createInitialState())
    val state: StateFlow<STATE> = _state.asStateFlow()

    private val _event = MutableSharedFlow<EVENT>()
    val event = _event.asSharedFlow()

    private val _effect = Channel<EFFECT>()
    val effect = _effect.receiveAsFlow()

    protected fun setState(reducer: STATE.() -> STATE) {
        _state.value = _state.value.reducer()
    }

    fun sendEvent(event: EVENT) {
        viewModelScope.launch(dispatcher.main) {
            _event.emit(event)
        }
    }

    protected fun sendEffect(builder: () -> EFFECT) {
        viewModelScope.launch(dispatcher.main) {
            _effect.send(builder())
        }
    }

    protected fun launchIO(block: suspend () -> Unit) {
        viewModelScope.launch(dispatcher.io) { block() }
    }

    protected fun launchMain(block: suspend () -> Unit) {
        viewModelScope.launch(dispatcher.main) { block() }
    }

    protected fun launchDefault(block: suspend () -> Unit) {
        viewModelScope.launch(dispatcher.default) { block() }
    }
}