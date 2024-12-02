package com.cyb.payten_windowsxp_terminalapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cyb.payten_windowsxp_terminalapp.receiver.PaymentReceiver
import com.cyb.payten_windowsxp_terminalapp.receiver.TxStatus
import com.cyb.payten_windowsxp_terminalapp.terminalNavigation.TerminalNavigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var paymentReceiver: PaymentReceiver? = null

    private var paymentState = MutableStateFlow<TxStatus?>(null)

    @SuppressLint("UnspecifiedRegisterReceiverFlag", "StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paymentReceiver = PaymentReceiver { txStatus ->
            paymentState.getAndUpdate { txStatus }
            Log.d("message--txState", "$txStatus")
        }
        setContent {
            val txState by paymentState.collectAsState()
            Log.d("message--txStateeeee222", "$txState")
            TerminalNavigation(
                txStatus = paymentState.value,
                "splash_screen"
            )
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(
                paymentReceiver,
                createPaymentReceiverIntentFilter(),
                RECEIVER_EXPORTED,
            )
        } else {
            registerReceiver(
                paymentReceiver,
                createPaymentReceiverIntentFilter(),
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        paymentReceiver?.let { unregisterReceiver(it) }
    }

    private fun createPaymentReceiverIntentFilter(): IntentFilter {
        val intentFilter = IntentFilter()
        intentFilter.addAction("senderIntentFilter")
        intentFilter.addAction("com.payten.devicemanager.DefaultEcrApp")
        return intentFilter
    }

//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        setIntent(intent)
//        handleIntent(intent)
//    }
//
//    private fun handleIntent(intent: Intent?) {
//        val navigateTo = intent?.getStringExtra("navigateTo")
//        Log.d("MainActivityyyy", "Navigating to: $navigateTo")
//        if (navigateTo != null) {
//            setContent {
//                TerminalNavigation(navigateTo)
//            }
//        }
//    }
}