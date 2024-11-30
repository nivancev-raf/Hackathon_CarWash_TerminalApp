package com.cyb.payten_windowsxp_terminalapp.payment

interface PaymentContract {
    data class PaymentContractUiState(
        val username: String = "",
        val membership: String = "",
        val priceOfToken: Float = 1f,
        val token: Int = 0,
        val time: Int = 0,
        val discount: Float = 0f,
        val discountToShow: Float = 0f,
        val basePrice: Float = 0f,
        val totalPrice: Float = 0f
    )

    sealed class PaymentContactUiEvent{
        data class ChangeTokenAmount(val value: Boolean): PaymentContactUiEvent()
        data class ClearDataStore(val value: Boolean): PaymentContactUiEvent()
        data class SetDiscount(val value: String): PaymentContactUiEvent()
        data class SetTotalPrice(val value: String): PaymentContactUiEvent()
    }
}