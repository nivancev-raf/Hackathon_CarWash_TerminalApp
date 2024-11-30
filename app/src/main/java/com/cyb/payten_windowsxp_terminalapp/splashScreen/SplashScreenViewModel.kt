package com.cyb.payten_windowsxp_terminalapp.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(

): ViewModel() {
    private val _state = MutableStateFlow(SplashScreenContract.SplashScreenUiState())
    val state = _state.asStateFlow()
    private fun setState(reducer: SplashScreenContract.SplashScreenUiState.() ->
    SplashScreenContract.SplashScreenUiState
    ) = _state.update(reducer)

    private val events = MutableSharedFlow<SplashScreenContract.SplashScreenUiEvent>()
    fun setEvent(event: SplashScreenContract.SplashScreenUiEvent) = viewModelScope.launch { events.emit(event) }

    init {
        //observeEvents()
    }

    private fun observeEvents() {

    }
}