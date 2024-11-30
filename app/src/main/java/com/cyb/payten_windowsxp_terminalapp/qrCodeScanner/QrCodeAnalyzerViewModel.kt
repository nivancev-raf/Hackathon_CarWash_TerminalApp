package com.cyb.payten_windowsxp_terminalapp.qrCodeScanner

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
class QrCodeAnalyzerViewModel @Inject constructor(

): ViewModel() {
    private val _state = MutableStateFlow(QrCodeAnalyzerContract.QrCodeAnalyzerUiState())
    val state = _state.asStateFlow()
    private fun setStete(reducer: QrCodeAnalyzerContract.QrCodeAnalyzerUiState.() -> QrCodeAnalyzerContract.QrCodeAnalyzerUiState) = _state.update(reducer)

    private val events = MutableSharedFlow<QrCodeAnalyzerContract.QrCodeAnalyzerUiEvent>()
    fun setEvent(event : QrCodeAnalyzerContract.QrCodeAnalyzerUiEvent) = viewModelScope.launch{events.emit(event)}

    init {

    }

}