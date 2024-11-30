package com.cyb.payten_windowsxp_terminalapp.qrCodeScanner

interface QrCodeAnalyzerContract {
    data class QrCodeAnalyzerUiState(
            var code: String = ""
    )

    sealed class QrCodeAnalyzerUiEvent {
        data class Test(val value: String) : QrCodeAnalyzerUiEvent()
    }
}