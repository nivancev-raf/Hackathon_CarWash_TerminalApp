package com.cyb.payten_windowsxp_terminalapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import org.json.JSONObject

class PaymentReceiver(
    val onResult: (TxStatus) -> Unit,
) : BroadcastReceiver() {
    var hashFinal: String = ""
    override fun onReceive(context: Context?, intent: Intent?) {
        val isSuccess = intent?.let {
            val response = intent.getStringExtra("ResponseResult")
            Log.d("message--response", response.toString())
            try {
                val jsonObject = JSONObject(response)
                val headerObject = jsonObject.getJSONObject("header")
                val hash = headerObject.getString("hash")
                hashFinal = hash
                val financialObject = jsonObject.getJSONObject("response").getJSONObject("financial")
                val resultObject = financialObject.getJSONObject("result")
                var message = resultObject.getString("message")
                Log.d("message--odgovor", message)
                if (message == "Odobreno") {
                    true
                } else {
                    false
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } == true

        if (isSuccess) {
            Log.d("message--hashFinal", hashFinal)
            onResult(
                TxStatus(txId = hashFinal, success = true)
            )
        } else {
            onResult(
                TxStatus(txId = hashFinal, success = false)
            )
        }
    }

}

data class TxStatus(
    val txId: String,
    val success: Boolean,
)