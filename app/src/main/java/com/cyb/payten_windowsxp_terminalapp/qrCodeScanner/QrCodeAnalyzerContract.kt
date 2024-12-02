package com.cyb.payten_windowsxp_terminalapp.qrCodeScanner

interface QrCodeAnalyzerContract {
    data class QrCodeAnalyzerUiState(
        var code: String = "",
        var loadedQR: Boolean = false
    )

    sealed class QrCodeAnalyzerUiEvent {
        data class SetDataStore(val value: String) : QrCodeAnalyzerUiEvent()
    }
}