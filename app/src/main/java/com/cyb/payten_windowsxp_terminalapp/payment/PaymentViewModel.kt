package com.cyb.payten_windowsxp_terminalapp.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyb.payten_windowsxp_terminalapp.auth.AuthData
import com.cyb.payten_windowsxp_terminalapp.auth.AuthStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val authStore: AuthStore
): ViewModel() {
    private val _state = MutableStateFlow(PaymentContract.PaymentContractUiState())
    val state = _state.asStateFlow()
    private fun setState(reducer: PaymentContract.PaymentContractUiState.() ->
    PaymentContract.PaymentContractUiState
    ) = _state.update(reducer)

    private val events = MutableSharedFlow<PaymentContract.PaymentContactUiEvent>()
    fun setEvent(event: PaymentContract.PaymentContactUiEvent) = viewModelScope.launch { events.emit(event) }

    init {
        populateState()
        observeTokens()
        observeClearDataStore()
    }

    private fun observeClearDataStore() {
        viewModelScope.launch {
            events
                .filterIsInstance<PaymentContract.PaymentContactUiEvent.ClearDataStore>()
                .collect {
                    authStore.updateAuthData(
                        AuthData(
                            user_id = -1,
                            first_name = "",
                            membership = "",
                            discount = 0f
                        )
                    )
                }
        }
    }

    private fun populateState() {
        val authData = authStore.authData.value

        if (authData.user_id != -1) {
            setState { copy(username = authData.first_name, membership = authData.membership, discount = authData.discount) }
        }
    }

    private fun observeTokens() {
        viewModelScope.launch {
            events
                .filterIsInstance<PaymentContract.PaymentContactUiEvent.ChangeTokenAmount>()
                .collect { event ->
                    if (event.value) {
                        setState { copy(token = state.value.token.inc()) }
                    } else {
                        if (state.value.token != 0) {
                            setState { copy(token = state.value.token.dec()) }
                        }
                    }

                    val time = state.value.token * 100
                    setState { copy(time = time) }

                    val basePrice = state.value.priceOfToken * state.value.token
                    val discount = basePrice * state.value.discount
                    val totalPrice = basePrice - discount

                    setState { copy(basePrice = basePrice, discountToShow = discount, totalPrice = totalPrice) }

                }
        }
    }
}